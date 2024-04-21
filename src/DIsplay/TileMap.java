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
            "......................................#......",
            "......................................#......",
            "......................................#......",
            "...............................1......#......",
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
    };

    private Sprite enemy;

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
                if (c != '.' && !Character.isDigit(c)) {
                    RectCollision tileRect = new RectCollision(col * getScale(),
                            row * getScale(), getScale(), getScale());
                    if (player.isOverlapping(tileRect)) {
                        player.pushedOutOf(tileRect);
                    }
                }
            }
        }
    }

    public void spawnSprite(char c) {
        switch (c){
            case '1':
                enemy = new Sprite(100, 500, 128, 128, "enemy-1");
                break;
            case '2':
                System.out.println("2");
            case '3':
                System.out.println("3");
            default:
                System.out.println("No matches");
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