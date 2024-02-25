package Game;

import Enemy.AnimationTest;
import ScreenManager.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame implements KeyListener {
    Image image;
    ScreenManager screenManager;

    AnimationTest animationTest;

    boolean right = false;

    public static void main(String[] args) {
        DisplayMode displayMode =
                    new DisplayMode(
                            800,
                            600,
                            16,
                            DisplayMode.REFRESH_RATE_UNKNOWN
                    );

        Main main = new Main();
        main.run(displayMode);
    }

    public void run(DisplayMode displayMode) {
        setBackground(Color.blue);
        setForeground(Color.white);

        screenManager = new ScreenManager();
        screenManager.setFullScreen(displayMode, this);

        animationTest = new AnimationTest(0, 0, "right-walk", 8, 10);

        this.addKeyListener(this);
        requestFocus();

        while (true) {
            Image currentFrame = animationTest.animate();
            loadImages(currentFrame);

            if(right) {
                animationTest.moveBy(+1, 0);
            }
            repaint();

            try {
                Thread.sleep(13);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadImages(Image frame) {
        image = frame;
    }


    public void paint(Graphics pen) {
        pen.clearRect(0, 0, getWidth(), getHeight());
        pen.drawString("Hello World!", 50, 50);
        pen.drawImage(image, animationTest.getX(), animationTest.getY(), null);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_RIGHT) {
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            right = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}