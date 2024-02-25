package Sprites;

import java.awt.*;

public class Enemy {

    private int x;
    private int y;

    private Animation idleLeft;
    private Animation idleRight;
    private Animation leftWalk;
    private Animation rightWalk;

    private Image currentFrame;

    private boolean wasLeft = false;

    public Enemy(int x, int y) {
        this.x = x;
        this.y = y;


        idleLeft =
                new Animation("enemy-1", "idle", "idle-left", 3, 15);
        idleRight =
                new Animation("enemy-1", "idle", "idle-right", 3, 15);
        leftWalk =
                new Animation("enemy-1", "walk", "left-walk", 7, 10);
        rightWalk =
                new Animation("enemy-1", "walk", "right-walk", 7, 10);
    }

    public void walkRight(int dx) {
        x += dx;
        wasLeft = false;
        currentFrame = rightWalk.animate();
    }

    public void walkLeft(int dx) {
        x -= dx;
        wasLeft = true;
        currentFrame = leftWalk.animate();
    }

    public void idle() {
        if(wasLeft) {
            currentFrame = idleLeft.animate();
        } else {
            currentFrame = idleRight.animate();
        }
    }

    public void paint(Graphics pen) {
        pen.drawImage(currentFrame, x, y, null);
    }
}
