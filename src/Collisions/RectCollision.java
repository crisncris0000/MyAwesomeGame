package Collisions;
import java.awt.*;

public class RectCollision {

    private int x;
    private int y;
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

    public void draw(Graphics pen) {
        pen.drawRect(x, y, width, height);
    }
}
