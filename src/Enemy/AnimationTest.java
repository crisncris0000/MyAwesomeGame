package Enemy;

import javax.swing.*;
import java.awt.*;

public class AnimationTest extends JFrame {

    private Image[] frames;
    private int next;

    private int currentFrameIndex;
    private int duration;
    private int x;

    private int y;

    private int delay;


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


}
