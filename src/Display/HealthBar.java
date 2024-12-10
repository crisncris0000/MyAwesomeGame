package Display;

import java.awt.*;

public class HealthBar {

    private int maxHealth;
    private int currentHealth;

    private int x;
    private int y;

    public HealthBar() {
        maxHealth = 100;
        currentHealth = maxHealth;
    }

    public HealthBar(int maxHealth) {
        this.maxHealth = maxHealth;
        currentHealth = maxHealth;
    }

    public void lowerHealthBy(int amount) {
        if(currentHealth - amount <= 0) {
            currentHealth = 0;
            return;
        }
        currentHealth -= amount;
    }

    public void gainHealthBy(int amount) {
        if(currentHealth + amount > maxHealth) {
            currentHealth = 100;
            return;
        }
        currentHealth += amount;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void draw(Graphics pen) {
        pen.setColor(Color.black);
        pen.setFont(new Font("Courier New", Font.PLAIN, 17));
        pen.drawRect(x, y, 500, 100);
        pen.drawString("Health: " + currentHealth + "/" + maxHealth, x + 190, y + 50);
    }
}
