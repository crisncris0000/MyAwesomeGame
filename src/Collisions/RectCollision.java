package Collisions;

import java.awt.*;

public class RectCollision {

    private int x;
    private int y;

    private double vx = 0;
    private double vy = 0;

    private static double g = .3;

    private int width;
    private int height;

    private Color color;

    public RectCollision(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public RectCollision(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    public boolean isOverlapping(RectCollision r) {
        return (x + width >= r.x      ) &&
                (x     <= r.x + r.width) &&
                (y + height >= r.y      ) &&
                (y     <= r.y + r.height);
    }

    public boolean isLeftOf(RectCollision r) {
        return x + width < r.x;
    }

    public boolean isRightOf(RectCollision r) {
        return x > r.x + width;
    }

    public boolean isAbove(RectCollision r) {
        return y + height < r.y;
    }

    public boolean isBelow(RectCollision r) {
        return y > r.y + height;
    }

    public void goLeft(int vx) {
        this.vx = -vx;
    }

    public void goRight(int vx) {
        this.vx = +vx;
    }

    public void setVelocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void pushedOutOf(RectCollision r)
    {
        if(cameFromAbove(r)) 	pushbackUpFrom(r);
        if(cameFromBelow(r))    pushbackDownFrom(r);
        if(cameFromLeftOf(r))   pushbackLeftFrom(r);
        if(cameFromRightOf(r))	pushbackRightFrom(r);
        vx = 0;
    }

    public boolean cameFromLeftOf(RectCollision r) {
        return x - vx + width < r.x;
    }

    public boolean cameFromRightOf(RectCollision r) {
        return r.x + r.width < x - vx;
    }

    public boolean cameFromAbove(RectCollision r) {
        return y - vy + height < r.y;
    }

    public boolean cameFromBelow(RectCollision r) {
        return r.y + r.height < y - vy;
    }

    public void pushbackLeftFrom(RectCollision r) {
        x = r.x - width - 1;
    }

    public void pushbackRightFrom(RectCollision r) {
        x = r.x + r.width + 1;
    }

    public void pushbackUpFrom(RectCollision r) {
        vy = 0;
        y = r.y - height - 1;
    }

    public void pushbackDownFrom(RectCollision r) {
        y = r.y + r.height+ 1;
    }

    public void move() {
        x += vx;
        y += vy;

        vy += g / 2;
        vy += g;
    }

    public void jump() {
        if(vy == 0.0)
            vy = -10;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void chase(RectCollision r, int dx)
    {
        if(isLeftOf(r))   goRight(dx);
        if(isRightOf(r))  goLeft(dx);
    }

    public void adjustCollisionSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw(Graphics pen) {
        pen.setColor(color);
        pen.drawRect(x, y, width, height);
    }
}
