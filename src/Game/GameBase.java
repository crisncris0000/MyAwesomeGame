package Game;
import DIsplay.ScreenManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public abstract class GameBase extends JFrame implements KeyListener {

    ScreenManager screenManager;

    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean upPressed = false;

    boolean numOnePressed = false;
    boolean numTwoPressed = false;
    boolean numThreePressed = false;
    boolean numFourPressed = false;

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

        if(code == KeyEvent.VK_LEFT) {
            leftPressed = true;
        }

        if(code == KeyEvent.VK_UP) {
            upPressed = true;
        }

        if(code == KeyEvent.VK_1) {
            numOnePressed = true;
        }

        if(code == KeyEvent.VK_2) {
            numTwoPressed = true;
        }

        if(code == KeyEvent.VK_3) {
            numThreePressed = true;
        }

        if(code == KeyEvent.VK_4) {
            numFourPressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_RIGHT) {
            rightPressed = false;
        }

        if(code == KeyEvent.VK_LEFT) {
            leftPressed = false;
        }

        if(code == KeyEvent.VK_UP) {
            upPressed = false;
        }

        if(code == KeyEvent.VK_4) {
            numFourPressed = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}