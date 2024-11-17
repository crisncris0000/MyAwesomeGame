package DIsplay;

import java.awt.*;

public class HealthBar {

    private int maxHealth;
    private int currentHealth;

    public HealthBar() {
        maxHealth = 100;
        currentHealth = maxHealth;
    }

    public HealthBar(int maxHealth) {
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
    }

    public void lowerHealthBy(int amount) {
        currentHealth -= amount;
    }

    public void draw(Graphics pen) {
        pen.setColor(Color.black);
        pen.setFont(new Font("Courier New", Font.PLAIN, 17));
        pen.drawRect(0, 300, 500, 100);
        pen.drawString("Health: " + currentHealth + "/" + maxHealth, 150, 350);
    }
}
