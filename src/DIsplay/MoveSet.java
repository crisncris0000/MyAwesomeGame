package DIsplay;

import java.awt.*;

public class MoveSet {

    private String[] moves;

    private boolean display;

    public MoveSet(String[] moves) {
        this.moves = moves;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public void draw(Graphics pen) {


        if(display) {
            pen.setColor(Color.blue);
            pen.setFont(new Font("Arial", Font.BOLD, 24));

            pen.drawRect(350, 0, 700, 200);

            pen.drawString(moves[0],550, 70);
            pen.drawString(moves[1],750, 70);
            pen.drawString(moves[2],550, 150);
            pen.drawString(moves[3],750, 150);
        }

    }
}
