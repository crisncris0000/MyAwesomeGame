package Collisions;
import java.awt.*;

public class RectCollision {

    private int x;
    private int y;

    private double vx = 0;
    private double vy = 0;
    private int width;
    private int height;

    public RectCollision(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean isOverlapping(RectCollision r) {
        return !isLeftOf(r) && !isRightOf(r) && !isAbove(r) && !isBelow(r);
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

    public void walkLeft(int vx) {
        this.vx = -vx;
    }

    public void walkRight(int vx) {
        this.vx = +vx;
    }

    public void setVelocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public void move() {
        x += vx;
        y += vy;

        vx = 0;
        vy = 0;
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
