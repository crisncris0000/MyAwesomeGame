package Game;
import DIsplay.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class GameBase extends JFrame implements KeyListener {

    ScreenManager screenManager;

    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean upPressed = false;

    public void init() {
        setForeground(Color.white);

        this.addKeyListener(this);
        requestFocus();
    }

    public void run(DisplayMode displayMode) {

        screenManager = new ScreenManager();
        screenManager.setFullScreen(displayMode, this);

        init();

        while (true) {
            repaint();
            inGameLoop();

            try {
                Thread.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public abstract void inGameLoop();

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
}