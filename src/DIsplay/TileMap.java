package DIsplay;

import Collisions.RectCollision;
import Sprites.Sprite;

import java.awt.*;

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

    public void checkCollision(Sprite player) {
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
            }2
        }
    }

    public Sprite createSprite(int num, int column, int row) {

        switch (num) {
            case 1:
                return new Sprite(column * getScale(), row * getScale(), 128, 128, "enemy-1");
            case 2:
                return new Sprite(column * getScale(), row * getScale(), 128, 128, "enemy-2");
            case 3:
                return new Sprite(column * getScale(), row * getScale(), 128, 128, "enemy-3");
            default:
                System.out.println("No condition set for " + num);
                break;
        }
        return null;
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