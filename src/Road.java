import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Road extends JPanel {
    // Quasi "Liste" für alle Fahrzeuge die drauf sind
    // JPanel wird zum zeichnen benutzt
    final int LANE_HEIGHT = 140;
    // nutzen Array damit alle Fahrzeuge für die Straße gespeoichert werden.
    ArrayList<Vehicle> cars = new ArrayList<Vehicle>();

    public Road() {
        super(); // calls JPanel constr
    }

    public void addCar(Vehicle v) {
        // fügt autos in cars hinzu
        cars.add(v);
    }

    // @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g); // clears the screen
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.setColor(Color.WHITE);
        for (int i = LANE_HEIGHT; i < LANE_HEIGHT * 4; i += LANE_HEIGHT) {// welche straße
            for (int j = 0; j < getWidth(); j += 55) {// welcher strich
                g.fillRect(j, i, 30, 5);

            }
        }
        for (int i = 0; i < cars.size(); i++) {
            cars.get(i).paintMe(g); // nimm das Auto aus cars und mals
        }
    }

    public void step() {
        for (int i = 0; i < cars.size(); i++) {
            Vehicle v = cars.get(i);
            v.setX(v.getX() + v.getSpeed());// erhöhung der alten position um die Geschwindigkeit speed
        }
    }
}
