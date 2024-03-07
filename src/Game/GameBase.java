package Game;
import Collisions.RectCollision;
import ScreenManager.ScreenManager;
import Sprites.Sprite;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class GameBase extends JFrame implements KeyListener {

    ScreenManager screenManager;

    boolean leftPressed = false;
    boolean rightPressed = false;
    boolean upPressed = false;

    private Image doubleBuffer;

    public void init() {
        setBackground(Color.gray);
        setForeground(Color.white);

        this.addKeyListener(this);
        requestFocus();
    }

    @Override
    public void update(Graphics g) {
        Dimension size = getSize();

        if (doubleBuffer == null ||
                doubleBuffer.getWidth(this) != size.width ||
                doubleBuffer.getHeight(this) != size.height)
        {
            doubleBuffer = createImage(size.width, size.height);
        }

        if(doubleBuffer != null){
            Graphics g2 = doubleBuffer.getGraphics();
            paint(g2);
            g2.dispose();

            g.drawImage(doubleBuffer, 0, 0, null);
        } else {
            paint(g);
        }
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