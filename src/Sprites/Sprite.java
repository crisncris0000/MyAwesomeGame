package Sprites;

import Collisions.RectCollision;
import DIsplay.HealthBar;
import DIsplay.MoveSet;
import java.awt.*;

public class Sprite extends RectCollision {

    private int width;
    private int height;

    private Animation idleLeft;
    private Animation idleRight;
    private Animation leftWalk;
    private Animation rightWalk;
    private Animation attack;
    private Animation effect;

    private HealthBar healthBar;

    private MoveSet moveSet;

    private Image currentFrame;

    private boolean revealRect = false;

    private boolean wasLeft = false;

    private int x;
    private int y;


    public Sprite(int x, int y, int width, int height, String spriteFolder, MoveSet moveSet, HealthBar healthBar,
                  int idleSize, int walkSize, int attackSize) {
        super(x, y, width, height);
        this.width = width;
        this.height = height;
        this.moveSet = moveSet;
        this.healthBar = healthBar;

        idleLeft =
                new Animation(spriteFolder, "idle", "idle-left", idleSize, 15);
        idleRight =
                new Animation(spriteFolder, "idle", "idle-right", idleSize, 15);

        leftWalk =
                new Animation(spriteFolder, "walk", "left-walk", walkSize, 10);
        rightWalk =
                new Animation(spriteFolder, "walk", "right-walk", walkSize, 10);

        attack =
                new Animation(spriteFolder, "attack", "attack", attackSize, 10);
    }

    public Sprite(int x, int y, int width, int height, String spriteFolder, String spriteEffect) {
        super(x, y, width, height);
        this.width = width;
        this.height = height;

        effect =
                new Animation(spriteFolder, spriteEffect, spriteEffect, 22, 3);
    }

    public boolean animateEffect() {
        boolean effectCompleted = false;

        if(effect.isLastFrame()) {
           effectCompleted = true;
        }
        currentFrame = effect.animate();

        return effectCompleted;
    }

    public void adjustPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }

    public MoveSet getMoveSet() {
        return moveSet;
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


    public boolean attack() {
        boolean attackCompleted = false;
        currentFrame = attack.animate();

        if(attack.isLastFrame()) {
            attackCompleted = true;
            attack.reset();
        }

        return attackCompleted;
    }

    public void setWasLeft(boolean wasLeft) {
        this.wasLeft = wasLeft;
    }

    public void idle() {
        if(wasLeft) {
            currentFrame = idleLeft.animate();
        } else {
            currentFrame = idleRight.animate();
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
