package Sprites;

import Collisions.RectCollision;

import java.awt.*;

public class Sprite extends RectCollision {

    private int x;
    private int y;

    private int width;
    private int height;

    private Animation idleLeft;
    private Animation idleRight;
    private Animation leftWalk;
    private Animation rightWalk;

    private Image currentFrame;

    private boolean wasLeft = false;

    public Sprite(int x, int y, int width, int height, String enemyFolder) {

        super(x, y + 20, width, height);

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
    public void walkLeft(int dx) {
        x -= dx;
        super.walkLeft(dx);
        wasLeft = true;
        currentFrame = leftWalk.animate();
    }

    @Override
    public void walkRight(int dx) {
        x += dx;
        super.walkRight(dx);
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

    public void paint(Graphics pen) {
        super.draw(pen);
        pen.drawImage(currentFrame, x, y, width, height, null);
    }
}
