package Sprites;

import javax.swing.*;
import java.awt.*;

public class Animation extends JFrame {

    private Image[] frames;
    private int next;
    private int duration;
    private int delay;


    public Animation(String spriteFolder, String subFolder, String name, int size, int duration){
        frames = new Image[size];
        this.duration = duration;

        for(int i = 0; i < frames.length; i++) {
            frames[i] = Toolkit.getDefaultToolkit()
                    .getImage("assets/" + spriteFolder + "/" + subFolder + "/" + name + "-" + i + ".png");
        }
        delay = duration;
    }

    public Image animate() {
        if(delay == 0) {
            next++;

            if (next == frames.length) next = 0;
            delay = duration;
        }

        delay--;

        return frames[next];
    }

    public boolean isLastFrame() {
        return next == frames.length - 1;
    }

    public void reset() {
        next = 0;
        delay = duration;
    }
}
