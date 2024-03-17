package Game;
import Collisions.RectCollision;
import DIsplay.TileMap;
import Sprites.Sprite;


import java.awt.*;

public class Main extends GameBase {

    TileMap map = new TileMap();

    int scale = map.getScale();

    String[] mapArr = map.getMap();

    Sprite enemy = new Sprite(0, 500, 128, 128, "enemy-1");

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

        enemy.resizeRect(70, enemy.getHeight());

        if(upPressed) {
            enemy.jump();
        }

        if(!leftPressed && !rightPressed) {
            enemy.idle();
        }
        if(leftPressed) {
            enemy.goLeft(scale/8);
        }

        if(rightPressed) {
            enemy.goRight(scale/8);
        }

        for (int row = 0; row < mapArr.length; row++) {
            for (int col = 0; col < mapArr[row].length(); col++) {
                char tile = mapArr[row].charAt(col);
                if (tile == '#') {
                    RectCollision tileRect = new RectCollision(col * map.getScale(),
                            row * map.getScale(), map.getScale(), map.getScale());
                    if (enemy.isOverlapping(tileRect)) {
                        enemy.pushedOutOf(tileRect);
                    }
                }
            }
        }

        enemy.move();
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
        enemy.draw(pen);
    }
}