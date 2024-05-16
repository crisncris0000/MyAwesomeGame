package Sprites;

import Collisions.RectCollision;
import DIsplay.MoveSet;

import javax.swing.*;
import java.awt.*;

public class Sprite extends RectCollision {

    private int width;
    private int height;

    private Animation idleLeft;
    private Animation idleRight;
    private Animation leftWalk;
    private Animation rightWalk;
    private Animation attack;


    private MoveSet moveSet;

    private Image currentFrame;

    private boolean revealRect = false;

    private boolean wasLeft = false;

    private int x;
    private int y;

    private boolean canMove = true;


    public Sprite(int x, int y, int width, int height, String spriteFolder, MoveSet moveSet) {
        super(x, y, width, height);
        this.width = width;
        this.height = height;
        this.moveSet = moveSet;

        idleLeft =
                new Animation(spriteFolder, "idle", "idle-left", 3, 15);
        idleRight =
                new Animation(spriteFolder, "idle", "idle-right", 3, 15);
        leftWalk =
                new Animation(spriteFolder, "walk", "left-walk", 7, 10);
        rightWalk =
                new Animation(spriteFolder, "walk", "right-walk", 7, 10);
        attack =
                new Animation(spriteFolder, "attack", "attack", 5, 10);
    }

    public Sprite(int x, int y, int width, int height, String spriteFolder, int idleDuration, int walkDuration) {
        super(x, y, 100, 150);

        this.width = width;
        this.height = height;

        idleLeft =
                new Animation(spriteFolder, "idle", "idle-left", 3, idleDuration);
        idleRight =
                new Animation(spriteFolder, "idle", "idle-right", 3, idleDuration);
        leftWalk =
                new Animation(spriteFolder, "walk", "left-walk", 7, walkDuration);
        rightWalk =
                new Animation(spriteFolder, "walk", "right-walk", 7, walkDuration);
        attack =
                new Animation(spriteFolder, "attack", "attack", 5, 10);
    }

    public void adjustPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void goLeft(int vx) {
        super.goLeft(vx);
        wasLeft = true;
        currentFrame = leftWalk.animate(true);
    }

    @Override
    public void goRight(int vx) {
        super.goRight(vx);
        wasLeft = false;
        currentFrame = rightWalk.animate(true);
    }

    int count = 0;

    @Override
    public void attack() {
        currentFrame = attack.animateOnce();
        if(count == 0) {
            if(attack.isLastFrame()) {
                count++;
            }
        } else {
            attack.reset();
            count = 0;
        }
    }


    public void idle() {
        if(wasLeft) {
            currentFrame = idleLeft.animate(true);
        } else {
            currentFrame = idleRight.animate(true);
        }
    }

    public void setRevealRect(boolean revealRect) {
        this.revealRect = revealRect;
    }

    public void draw(Graphics pen) {
        pen.drawImage(currentFrame, getX() - x, getY() - y, width, height, null);
        if(revealRect) {
            super.draw(pen);
        }
    }
}
