package Display;

import java.awt.*;

public class Menu {

    private String[] options = {"Start Game", "Instructions", "Exit"};
    private int currentSelection = 0;
    private boolean visible = true;

    public void navigateUp() {
        currentSelection = (currentSelection - 1 + options.length) % options.length;
    }

    public void navigateDown() {
        currentSelection = (currentSelection + 1) % options.length;
    }

    public String getSelectedOption() {
        return options[currentSelection];
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVisible() {
        return visible;
    }

    public void draw(Graphics pen, int width, int height) {
        if (!visible) {
            return;
        }

        pen.setColor(Color.BLACK);
        pen.fillRect(0, 0, width, height);

        pen.setColor(Color.WHITE);
        pen.setFont(new Font("Arial", Font.BOLD, 48));
        pen.drawString("MAIN MENU", width / 2 - 150, height / 4);

        pen.setFont(new Font("Arial", Font.PLAIN, 32));
        for (int i = 0; i < options.length; i++) {
            if (i == currentSelection) {
                pen.setColor(Color.YELLOW);
            } else {
                pen.setColor(Color.WHITE);
            }
            pen.drawString(options[i], width / 2 - 100, height / 2 + i * 50);
        }
    }
}
