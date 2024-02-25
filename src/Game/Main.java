package Game;

import Sprites.Animation;
import ScreenManager.ScreenManager;
import Sprites.Enemy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame implements KeyListener {

    private ScreenManager screenManager;
    private Enemy enemy;

    private boolean leftPressed = false;
    private boolean rightPressed = false;






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

        enemy = new Enemy(0, 0);

        screenManager = new ScreenManager();
        screenManager.setFullScreen(displayMode, this);

        this.addKeyListener(this);
        requestFocus();

        while (true) {

            if(!leftPressed && !rightPressed) {
                enemy.idle();
            }

            if(leftPressed) {
                enemy.walkLeft(1);
            }

            if(rightPressed) {
                enemy.walkRight(1);
            }

            repaint();

            try {
                Thread.sleep(13);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void paint(Graphics pen) {
        pen.clearRect(0, 0, getWidth(), getHeight());
        pen.drawString("Hello World!", 50, 50);
        enemy.paint(pen);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_RIGHT) {
            rightPressed = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}