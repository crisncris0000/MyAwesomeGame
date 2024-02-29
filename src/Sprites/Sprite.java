package Sprites;

import Collisions.RectCollision;

import java.awt.*;

public class Sprite extends RectCollision {

    private int x;
    private int y;

    double vx = 0;
    double vy = 0;

    private int width;
    private int height;

    private Animation idleLeft;
    private Animation idleRight;
    private Animation leftWalk;
    private Animation rightWalk;

    private Image currentFrame;

    private boolean wasLeft = false;

    public Sprite(int x, int y, int width, int height, String enemyFolder) {

        super(x, y, width, height);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;


        idleLeft =
                new Animation(enemyFolder, "idle", "idle-left", 3, 15);
        idleRight =
                new Animation(enemyFolder, "idle", "idle-right", 3, 15);
        leftWalk =
                new Animation(enemyFolder, "walk", "left-walk", 7, 10);
        rightWalk =
                new Animation(enemyFolder, "walk", "right-walk", 7, 10);
    }

    public Sprite(int x, int y, int width, int height, String enemyFolder, int idleDuration, int walkDuration) {
        super(x, y, 100, 150);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        idleLeft =
                new Animation(enemyFolder, "idle", "idle-left", 3, idleDuration);
        idleRight =
                new Animation(enemyFolder, "idle", "idle-right", 3, idleDuration);
        leftWalk =
                new Animation(enemyFolder, "walk", "left-walk", 7, walkDuration);
        rightWalk =
                new Animation(enemyFolder, "walk", "right-walk", 7, walkDuration);
    }

    @Override
    public void walkLeft(int vx) {
        super.walkLeft(vx);
        wasLeft = true;
        currentFrame = leftWalk.animate();
    }

    @Override
    public void walkRight(int vx) {
        super.walkRight(vx);
        wasLeft = false;
        currentFrame = rightWalk.animate();
    }


    public void idle() {
        if(wasLeft) {
            currentFrame = idleLeft.animate();
        } else {
            currentFrame = idleRight.animate();
        }
    }

    public void setVelocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void paint(Graphics pen) {
        super.paint(pen);
        pen.drawImage(currentFrame, getX(), getY(), width, height, null);
    }
}
