package DIsplay;

import Collisions.RectCollision;
import Sprites.Sprite;

import java.awt.*;
import java.util.Random;

public class TileMap {

    String[] map = {
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            "......................................#......",
            "......................................#......",
            "......................................#......",
            "...............................1......#......",
            "......................................#......",
            "......................................#......",
            "......................................#......",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
    };

    String[] battleMap = {
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            ".............................................",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
    };



    private final int size = 10000;

    private boolean[] encounter = new boolean[size];

    static final int scale = 32;

    public int getScale() {
       return scale;
    }

    public String[] getMap() {
        return map;
    }

    public Image loadImage(String folder,String filename) {
        return Toolkit.getDefaultToolkit().getImage("assets/" + folder + "/" + filename);
    }

    public boolean randomEncounter() {
        for(int i = 0; i <= 10; i++) {
            encounter[i] = true;
        }
        Random random = new Random();
        return encounter[random.nextInt(size)];
    }

    public void checkCollisions(Sprite player) {
        for(int row = 0; row < map.length; row++) {
            for(int col = 0; col < map[row].length(); col++) {
                char c = map[row].charAt(col);
                if (c != '.'  && !Character.isDigit(c)) {
                    RectCollision tileRect = new RectCollision(col * getScale(),
                            row * getScale(), getScale(), getScale());
                    if (player.isOverlapping(tileRect)) {
                        player.pushedOutOf(tileRect);
                    }
                }
            }
        }
    }


    public void draw(Graphics pen) {
        pen.drawImage(loadImage("background", "background-0.png"),
                0, 0,1920,1080,  null);
        for(int row = 0; row < map.length; row++) {
            for(int column = 0; column < map[row].length(); column++) {
                char c = map[row].charAt(column);

                if(c == 'A') {
                    pen.drawImage(loadImage("tiles","ground-1.png"),
                            scale * column, scale * row, scale, scale, null);
                } else if(c == '#') {
                    pen.drawImage(loadImage("tiles", "wooden-crate.png"),
                            scale * column, scale * row, scale, scale, null);
                }
            }
        }
    }
}