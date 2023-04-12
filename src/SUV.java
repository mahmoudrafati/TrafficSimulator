import java.awt.Color;
import java.awt.Graphics;

public class SUV extends Vehicle {
    public SUV(int newX, int newY) {
        super(newX, newY);
        width = 45;
        height = 40;
        speed = 10;
    }

    public void paintMe(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y, width, height);
    }
}
