package Game;
import DIsplay.MoveSet;
import DIsplay.TileMap;
import Sprites.Sprite;


import java.awt.*;

public class Main extends GameBase {

    TileMap map = new TileMap();

    int scale = map.getScale();

    MoveSet playerMoveSet = new MoveSet(new String[]{"Attack", "Water", "Fire", "Defend"});

    Sprite player = new Sprite(0, 500, 128, 128, "player", playerMoveSet);

    Sprite enemy = new Sprite(0, 500, 128, 128, "enemy-1", playerMoveSet);

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
        enemy.move();
        enemy.setWasLeft(true);
    }



    public void handlePressedKeys() {

        if(!isBattling) {
            if(upPressed) {
                player.jump();
            }

            if(!leftPressed && !rightPressed) {
                player.idle();
            } else {
                if(map.randomEncounter()) {
                    map.displayBattleMap();
                    playerMoveSet.setDisplay(true);
                    repositionPlayer();
                }
            }

            if(leftPressed) {
                player.goLeft(scale/8);
            }

            if(rightPressed) {
                player.goRight(scale/8);
            }
        }

        if(numOnePressed) {
            player.attack();
        }
    }

    public void repositionPlayer() {
        isBattling = true;

        player.setX(100);
        player.idle();

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

        playerMoveSet.draw(pen);

    }
}