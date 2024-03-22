package Sprites;

import Collisions.RectCollision;

import java.awt.*;

public class Sprite extends RectCollision {

    private int width;
    private int height;

    private Animation idleLeft;
    private Animation idleRight;
    private Animation leftWalk;
    private Animation rightWalk;

    private Image currentFrame;

    private boolean wasLeft = false;

    private int x;
    private int y;


    public Sprite(int x, int y, int width, int height, String enemyFolder) {
        super(x, y, width, height);
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

    public void adjustPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void goLeft(int vx) {
        super.goLeft(vx);
        wasLeft = true;
        currentFrame = leftWalk.animate();
    }

    @Override
    public void goRight(int vx) {
        super.goRight(vx);
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

    public void draw(Graphics pen) {
        pen.drawImage(currentFrame, getX() - x, getY() - y, width, height, null);
        super.draw(pen);
    }
}
