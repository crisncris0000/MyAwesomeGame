package Game;

import Enemy.AnimationTest;
import ScreenManager.ScreenManager;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private static final long DEMO_TIME = 8000;
    private Image testImage;
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


        AnimationTest animationTest = new AnimationTest("left-walk", 8, 10);

        while (true) {
            Image currentFrame = animationTest.animate();
            System.out.println(currentFrame);
            loadImages(currentFrame);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadImages(Image frame) {
        testImage = frame;
        imagesLoaded = true;
        repaint();
    }

    private Image loadImage(String filename) {
        return new ImageIcon(filename).getImage();
    }


    public void paint(Graphics pen) {
        pen.drawString("Hello World!", 50, 50);
        pen.drawImage(testImage, 50, 50, this);
    }

}