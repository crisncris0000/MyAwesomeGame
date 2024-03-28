package DIsplay;

import Collisions.RectCollision;

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
        return Toolkit.getDefaultToolkit().getImage("assets/" +folder + "/" + filename);
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
                    pen.drawImage(loadImage("tiles","wooden-crate.png"),
                            scale * column, scale * row, scale + 10, scale + 10, null);
                }
            }
        }
    }
}