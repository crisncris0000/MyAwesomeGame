package Enemy;

import java.awt.*;

public class AnimationTest {

    private Image[] frames;
    private int next;

    private int currentFrameIndex;
    private int duration;

    private int delay;


    public AnimationTest(String name, int size, int duration){
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
