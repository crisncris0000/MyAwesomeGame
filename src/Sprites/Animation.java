package Sprites;

import javax.swing.*;
import java.awt.*;

public class Animation extends JFrame {

    private Image[] frames;
    private Image[] idle;
    private int next;
    private int duration;
    private int delay;


    public Animation(String enemyFolder, String subFolder, String name, int size, int duration){
        frames = new Image[size];
        this.duration = duration;

        for(int i = 0; i < frames.length; i++) {
            frames[i] = Toolkit.getDefaultToolkit()
                    .getImage("assets/" + enemyFolder + "/" + subFolder + "/" + name + "-" + i + ".png");
        }

        delay = duration;
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
