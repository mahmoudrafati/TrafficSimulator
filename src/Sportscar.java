import java.awt.Color;
import java.awt.Graphics;

public class Sportscar extends Vehicle {

    public Sportscar(int newX, int newY) {
        super(newX, newY);// Ruft Vehicle construcor
        width = 40;
        height = 30;
        speed = 20;

    }

    public void paintMe(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }

}
