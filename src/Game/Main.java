package Game;
import Collisions.RectCollision;
import Sprites.Sprite;


import java.awt.*;

public class Main extends GameBase {

    Sprite enemy = new Sprite(0, 500, 150, 150, "enemy-1");
    RectCollision floor = new RectCollision(0, 700, 1800, 50);

    public static void main(String[] args) {
        Main main = new Main();
        main.displayGame();
    }

    public void displayGame(){
        DisplayMode displayMode =
                new DisplayMode(
                        800,
                        600,
                        16,
                        DisplayMode.REFRESH_RATE_UNKNOWN
                );
        super.run(displayMode);
    }


    @Override
    public void inGameLoop() {

        if(enemy.isOverlapping(floor)) {
            enemy.pushedOutOf(floor);
        }

        if(!leftPressed && !rightPressed) {
            enemy.idle();
        }

        if(leftPressed) {
            enemy.goLeft(1);
        }

        if(rightPressed) {
            enemy.goRight(1);
        }

        if(upPressed) {
            enemy.jump();
        }

        enemy.move();
    }

    public void paint(Graphics pen) {
        pen.clearRect(0, 0, getWidth(), getHeight());
        enemy.paint(pen);
        floor.paint(pen);
    }
}