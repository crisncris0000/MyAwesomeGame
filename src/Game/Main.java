package Game;
import DIsplay.MoveSet;
import DIsplay.TileMap;
import Sprites.Sprite;


import java.awt.*;

public class Main extends GameBase {

    TileMap map = new TileMap();

    int scale = map.getScale();

    MoveSet moveSet = new MoveSet(new String[]{"1. Attack", "2. Water", "3. Fire", "4. Defend"});

    Sprite player = new Sprite(0, 500, 128, 128, "player", moveSet);

    Sprite enemy = new Sprite(0, 575, 128, 128, "enemy-1", moveSet);

    boolean isBattling = false;

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
        player.adjustPosition(35, 20);
        player.adjustCollisionSize(50, 110);

        handlePressedKeys();

        map.checkCollisions(player);
        map.checkCollisions(enemy);

        player.move();
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

        if(numOnePressed) {
          boolean attackCompleted = player.attack(false);

          if(attackCompleted) {
              numOnePressed = false;
              player.idle();
          }
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
                // Perform drawing operations on the off-screen buffer
                draw(offScreenGraphics);

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
        enemy.draw(pen);

        moveSet.draw(pen);

    }
}