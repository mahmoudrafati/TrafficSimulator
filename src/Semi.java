import java.awt.Color;
import java.awt.Graphics;

public class Semi extends Vehicle {
    // unterklasse von Fahrzeug
    public Semi(int newX, int newY) {
        super(newX, newY);// Rufen Vehicle Konstruktor auf
        // Werte spezifisch für Semi
        width = 120;
        height = 40;
        speed = 5;
    }

    public void paintMe(Graphics g) {
        // Once set, blauer semi wird generiert
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }

}
