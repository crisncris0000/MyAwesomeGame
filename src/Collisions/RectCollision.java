package Collisions;
import java.awt.*;

public class RectCollision {

    private int x;
    private int y;

    private double vx = 0;
    private double vy = 0;

    private static double g = .5;

    private int width;
    private int height;

    public RectCollision(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
        y = r.y - height - 1;
    }

    public void pushbackDownFrom(RectCollision r) {
        y = r.y + r.height+ 1;
    }

    public void move() {
        x += vx;
        y += vy;

        vy += g;
    }

    public void jump() {
        vy = -10;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void paint(Graphics pen) {
        pen.drawRect(x, y, width, height);
    }
}
