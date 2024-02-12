package Game;

import ScreenManager.SimpleScreenManager;

import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    private static final long DEMO_TIME = 5000;
    private Image testImage;
    private boolean imagesLoaded;
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

        ImageIcon icon =
                new ImageIcon("../../assets/tanks/tank_darkLarge.png");

        main.run(displayMode);
    }

    public void run(DisplayMode displayMode) {
        setBackground(Color.blue);
        setForeground(Color.white);

        SimpleScreenManager screenManager = new SimpleScreenManager();

        try {
            screenManager.setFullScreen(displayMode, this);
            loadImages();
            try {
                Thread.sleep(DEMO_TIME);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        } finally {
            screenManager.restoreScreen();
        }
    }

    public void loadImages() {
        testImage =
                loadImage("../assets/tanks/tank_darkLarge.png");
        imagesLoaded = true;
        repaint();
    }

    private Image loadImage(String filename) {
        return new ImageIcon(filename).getImage();
    }


    public void paint(Graphics pen) {
        pen.drawString("Hello World!", 20, 50);

        if(imagesLoaded) {
            pen.drawImage(testImage, 0, 0, null);
        }
    }

    public void drawImage(Graphics pen, Image image, int x, int y) {

    }
}