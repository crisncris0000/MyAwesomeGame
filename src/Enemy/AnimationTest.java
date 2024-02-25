package Enemy;

import javax.swing.*;
import java.awt.*;

public class AnimationTest extends JFrame {

    private Image[] frames;
    private int next;
    private int duration;
    private int delay;

    int x;
    int y;


    public AnimationTest(int x, int y, String name, int size, int duration){

        this.x = x;
        this.y = y;

        frames = new Image[size];
        this.duration = duration;

        for(int i = 0; i < frames.length; i++) {
            frames[i] = Toolkit.getDefaultToolkit()
                    .getImage("assets/enemy-1/enemy-walk/" + name + "-" + i + ".png");
        }
        delay = duration;
    }

    public Image idle() {
        return frames[0];
    }

    public Image animate() {
        if(delay == 0) {
            next++;

            if (next == frames.length) next = 1;
            delay = duration;
        }

        delay--;

        return frames[next];
    }

    public void moveBy(int dx, int dy) {
        x += dx;

        y += dy;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
