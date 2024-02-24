package Game;

import Enemy.AnimationTest;
import ScreenManager.ScreenManager;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {
    private Image image;
    private boolean imagesLoaded;
    ScreenManager screenManager;
    public static void main(String[] args) {
        DisplayMode displayMode;

        if(args.length == 3) {
            displayMode = new DisplayMode(
                    Integer.parseInt(args[0]),
                    Integer.parseInt(args[1]),
                    Integer.parseInt(args[2]),
                    DisplayMode.REFRESH_RATE_UNKNOWN
            );
        } else {
            displayMode =
                    new DisplayMode(
                            800,
                            600,
                            16,
                            DisplayMode.REFRESH_RATE_UNKNOWN
                    );
        }


        Main main = new Main();
        main.run(displayMode);
    }

    public void run(DisplayMode displayMode) {
        setBackground(Color.blue);
        setForeground(Color.white);

        screenManager = new ScreenManager();

        screenManager.setFullScreen(displayMode,this);


        AnimationTest animationTest = new AnimationTest(0, 0,"left-walk", 8, 10);

        while (true) {
            Image currentFrame = animationTest.animate();
            loadImages(currentFrame);

            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadImages(Image frame) {
        image = frame;
        imagesLoaded = true;
    }


    public void paint(Graphics pen) {
        pen.clearRect(0, 0, getWidth(), getHeight());

        pen.drawString("Hello World!", 50, 50);
        pen.drawImage(image, 50, 50, null);
    }

}