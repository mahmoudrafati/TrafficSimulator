import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Road extends JPanel {
    // Quasi "Liste" für alle Fahrzeuge die drauf sind
    // JPanel wird zum zeichnen benutzt
    final int LANE_HEIGHT = 120;
    final int ROAD_WIDTH = 1050;
    int carCount = 0; // Zählt autos und updated immer wenn das "ende" der Straße erreicht ist
    long starttime = 0; //

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
            if (collision(v.getX() + v.getSpeed(), v.getY(), v) == false) {// Wir überprüfen ob das
                                                                           // erstelllte V einer collision
                                                                           // nicht zugehörig ist
                v.setX(v.getX() + v.getSpeed());// erhöhung der alten position um die Geschwindigkeit speed
                if (v.getX() > ROAD_WIDTH) {
                    if ((collision(0, v.getY(), v) == false)) {
                        v.setX(-50);
                        carCount++;
                    }
                }
            } else { // wenn auto davor ist
                if ((v.getY() > 40) && (collision(v.getX(), v.getY() - LANE_HEIGHT, v) == false)) { // wenn in linkste
                                                                                                    // Spur jemand davor
                                                                                                    // und aber rechter
                                                                                                    // auch platz ist.
                    v.setY(v.getY() - LANE_HEIGHT);
                } else if ((v.getY() < 40 + 3 * LANE_HEIGHT)
                        && (collision(v.getX(), v.getY() + LANE_HEIGHT, v) == false)) {
                    v.setY(v.getY() + LANE_HEIGHT);
                }
            }
        }
    }

    // Kollisionsfunktion
    public boolean collision(int x, int y, Vehicle v) {
        for (int i = 0; i < cars.size(); i++) {
            Vehicle u = cars.get(i);
            if (y == u.getY()) { // wenn man in der selben Spur ist
                if (u.equals(v) == false) { // sichergehen nicht selber gemeint ist
                    if (x < (u.getX() + u.getWidth()) && (x + v.getWidth()) > u.getX()) {
                        // Wenn mein Tail kleiner ist als hintermanns länge
                        // oder wenn meine Länge länger ist als vordermanns tail
                        return true;
                    }

                }
            }

        }
        return false;
    }

    public int getCarcount() {
        return carCount;
    }

    public void resetCarCount() {
        carCount = 0;
    }
}
