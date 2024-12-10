package Game;
import Display.HealthBar;
import Display.MoveSet;
import Display.TileMap;
import Sprites.Sprite;
import Display.Menu;
import java.awt.*;
import java.util.Random;

public class Main extends GameBase {

    TileMap map = new TileMap();

    HealthBar healthBar = new HealthBar(100);

    int scale = map.getScale();

    MoveSet moveSet = new MoveSet(new String[]{"1. Attack", "2. Water", "3. Fire", "4. Heal"});

    Sprite player = new Sprite(0, 500, 128, 128, "player", moveSet,
            healthBar, 6, 8, 5);

    Sprite enemy = new Sprite(0, 575, 128, 128, "enemy-1", moveSet,
            healthBar, 6, 7, 10);

    Sprite water = new Sprite(0, enemy.getY(), 128, 128, "effects", "water");

    Sprite fire = new Sprite(0, enemy.getY(), 128, 128, "effects", "fire");

    Menu menu = new Menu();

    boolean isBattling = false;
    boolean playerTurn = true;
    boolean enemyTurn = false;

    public static void main(String[] args) {
        Main main = new Main();
        main.displayGame();
    }

    public void displayGame(){
        DisplayMode displayMode =
                new DisplayMode(
                        800,
                        600,
                        16,
                        DisplayMode.REFRESH_RATE_UNKNOWN
                );
        super.run(displayMode);
    }

    @Override
    public void inGameLoop() {

        if(menu.isVisible()) {
            handleMenuNavigation();
        } else {
            player.adjustPosition(35, 20);
            player.adjustCollisionSize(50, 110);

            handlePressedKeys();

            map.checkCollisions(player);
            map.checkCollisions(enemy);

            player.getHealthBar().setX(player.getX());
            player.getHealthBar().setY(player.getY() - 200);

            player.move();
        }
    }

    public void handlePressedKeys() {
        if(!isBattling) {
            if(upPressed) {
                player.jump();
            }

            if((!leftPressed && !rightPressed) || isBattling) {
                player.idle();
            } else {
                if(map.randomEncounter()) {
                    player.setWasLeft(false);
                    player.idle();

                    beginBattle();
                    map.displayBattleMap();
                    moveSet.setDisplay(true);
                }
            }

            if(leftPressed && !isBattling) {
                player.goLeft(scale/8);
            }

            if(rightPressed && !isBattling) {
                player.goRight(scale/8);
            }
        }

        if(isBattling) {
            handleBattleLogic();
        }
    }

    private void handleMenuNavigation() {
        if (upPressed) {
            menu.navigateUp();
            upPressed = false;
        }
        if (downPressed) {
            menu.navigateDown();
            downPressed = false;
        }
        if (numOnePressed) {
            String selectedOption = menu.getSelectedOption();
            switch (selectedOption) {
                case "Start Game":
                    menu.setVisible(false);
                    break;
                case "Instructions":
                    System.out.println("Instructions: Use arrow keys to navigate, press 1-4 to choose actions.");
                    break;
                case "Exit":
                    System.exit(0);
                    break;
            }
            numOnePressed = false;
        }
    }

    boolean showWaterEffect = false;
    boolean showFireEffect = false;

    boolean enemyAttackPlaying = false; // Track if the enemy's attack animation is playing

    boolean moveChosen = false;
    String chosenMove = "";

    private void handleBattleLogic() {

        if(!moveChosen) {
            // regex for any non-alphabetic characters a-z
            chosenMove = enemy.randomChosenAttack().replaceAll("[^a-zA-Z]", "");
            moveChosen = true;
        }

        if (playerTurn) {
            water.setX(enemy.getX());
            fire.setX(enemy.getX());

            if (numOnePressed) {
                boolean attackCompleted = player.attack();
                if (attackCompleted) {
                    numOnePressed = false;
                    player.idle();
                    playerTurn = false;
                    enemyTurn = true;
                }
            } else if (numTwoPressed) {
                boolean attackCompleted = player.attack();
                showWaterEffect = true;
                if (attackCompleted) {
                    numTwoPressed = false;
                    player.idle();
                    playerTurn = false;
                    enemyTurn = true;
                }

                if (showWaterEffect) {
                    boolean waterEffectCompleted = water.animateEffect();
                    if (waterEffectCompleted) {
                        showWaterEffect = false;
                        water.setX(enemy.getX());
                    }
                }

                if (showFireEffect) {
                    boolean fireEffectCompleted = fire.animateEffect();
                    if (fireEffectCompleted) {
                        showFireEffect = false;
                        fire.setX(enemy.getX());
                    }
                }
            } else if (numThreePressed) {
                boolean attackCompleted = player.attack();
                showFireEffect = true;
                if (attackCompleted) {
                    numThreePressed = false;
                    player.idle();
                    playerTurn = false;
                    enemyTurn = true;
                }
            } else if (numFourPressed) {
                player.getHealthBar().gainHealthBy(new Random().nextInt(5, 10));
                enemyTurn = true;
                playerTurn = false;
            }
        }

        if (enemyTurn) {
            if (!enemyAttackPlaying) {
                enemyAttackPlaying = true; // Start the enemy's attack animation
                enemy.attack();

            } else {
                boolean attackCompleted = enemy.attack(); // Check if animation is done

                if(chosenMove.equals("Water")) {
                    showWaterEffect = true;
                } else if(chosenMove.equals("Fire")) {
                    showFireEffect = true;
                }

                if (showWaterEffect) {
                    boolean waterEffectCompleted = water.animateEffect();
                    if (waterEffectCompleted) {
                        water.setX(player.getX());
                        showWaterEffect = false;
                    }
                }

                if (showFireEffect) {
                    boolean fireEffectCompleted = fire.animateEffect();
                    if (fireEffectCompleted) {
                        fire.setX(player.getX());
                        showFireEffect = false;
                    }
                }

                if (attackCompleted) {
                    enemyAttackPlaying = false; // Reset flag
                    enemyTurn = false; // End enemy turn
                    playerTurn = true; // Give control back to the player
                    moveChosen = false;
                    enemy.idle();

                    player.getHealthBar().lowerHealthBy(new Random().nextInt(1,20));
                }
            }
        }

        if(player.getHealthBar().getCurrentHealth() <= 0) {
            isBattling = false;
            player.getHealthBar().setCurrentHealth(100);
            showWaterEffect = false;
            showFireEffect = false;
        }
    }


    public void beginBattle() {
        isBattling = true;

        player.setX(100);
        player.setY(550);

        enemy.move();
        enemy.setWasLeft(true);

        enemy.setX(900);
        enemy.idle();
    }

    public void paint(Graphics pen) {
        // Create off-screen buffer
        Image offScreenBuffer = createImage(getWidth(), getHeight());
        if (offScreenBuffer != null) {
            Graphics offScreenGraphics = offScreenBuffer.getGraphics();
            if (offScreenGraphics != null) {
                if (menu.isVisible()) {
                    menu.draw(offScreenGraphics, getWidth(), getHeight());
                } else {
                    draw(offScreenGraphics);
                }
                // Draw the off-screen buffer onto the screen
                pen.drawImage(offScreenBuffer, 0, 0, null);
                offScreenGraphics.dispose();
            }
        }
    }

    private void draw(Graphics pen) {
        pen.clearRect(0, 0, getWidth(), getHeight());
        map.draw(pen);
        player.draw(pen);

        if(isBattling) {
            enemy.draw(pen);
            player.getHealthBar().draw(pen);
            player.getMoveSet().draw(pen);
        }

        if(showWaterEffect) {
            water.draw(pen);
        }

        if(showFireEffect) {
            fire.draw(pen);
        }
    }
}