package Game;
import Collisions.RectCollision;
import ScreenManager.ScreenManager;
import Sprites.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JFrame implements KeyListener {

    private ScreenManager screenManager;
    private Sprite enemy;

    private boolean leftPressed = false;
    private boolean rightPressed = false;
    private boolean upPressed = false;

    RectCollision rect = new RectCollision(0, 700, 1800, 50);


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

    public void gameSetUp() {
        setBackground(Color.gray);
        setForeground(Color.white);

        enemy = new Sprite(0, 500, 150, 150, "enemy-1");

        this.addKeyListener(this);
        requestFocus();
    }

    public void run(DisplayMode displayMode) {

        screenManager = new ScreenManager();
        screenManager.setFullScreen(displayMode, this);

        gameSetUp();


        while (true) {

            if(!leftPressed && !rightPressed) {
                enemy.idle();
            }

            if(leftPressed) {
                enemy.goLeft(1);
            }

            if(rightPressed) {
                enemy.goRight(1);
            }

            if(upPressed) {
                enemy.jump();
            }

            enemy.move();

            if(enemy.isOverlapping(rect)) {
                enemy.pushedOutOf(rect);
                enemy.vx = 0;
                enemy.vy = 0;
            }

            repaint();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = true;
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

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            upPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public void paint(Graphics pen) {
        pen.clearRect(0, 0, getWidth(), getHeight());
        enemy.paint(pen);
        rect.paint(pen);
    }
}