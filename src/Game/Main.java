package Game;
import Collisions.RectCollision;
import DIsplay.TileMap;
import Sprites.Sprite;


import java.awt.*;

public class Main extends GameBase {

    TileMap map = new TileMap();

    int scale = map.getScale();

    String[] mapArr = map.getMap();

    Sprite player = new Sprite(0, 500, 128, 128, "player");

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

    public void buildMap() {
        for(int row = 0; row < mapArr.length; row++) {
            for(int col = 0; col < mapArr[row].length(); col++) {
                char tile = mapArr[row].charAt(col);
                if (tile != '.') {
                    RectCollision tileRect = new RectCollision(col * map.getScale(),
                            row * map.getScale(), map.getScale(), map.getScale());
                    if (player.isOverlapping(tileRect)) {
                        player.pushedOutOf(tileRect);
                    }
                }
            }
        }
    }

    @Override
    public void inGameLoop() {
        player.adjustPosition(35, 0);
        player.adjustCollisionSize(60, 128);

        buildMap();

        if(upPressed) {
            player.jump();
        }

        if(!leftPressed && !rightPressed) {
            player.idle();
        }
        if(leftPressed) {
            player.goLeft(scale/8);
        }

        if(rightPressed) {
            player.goRight(scale/8);
        }

        player.move();
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
    }
}