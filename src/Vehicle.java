import java.awt.Graphics;

public class Vehicle {

    int x, y; // int werte für die Klasse Fahrzeug
    int width = 0;
    int height = 0;
    int speed = 0;

    public Vehicle(int newX, int newY) {
        x = newX;
        y = newY;
        // Wir geben den Übergebenen x und y wert an x und y damit das weitergeleitet
        // werden kann
    }

    public void paintMe(Graphics g) {
        // eigene Paint methode für die Fahrzeuge wird jedoch in den Subklassen
        // übernommen
    }

    // Setter und getter zum ändern der Fahrzeugpositionen gilt so für autoarten
    public int getX() {
        return x;
    }

    public void setX(int newX) {
        this.x = newX;
    }

    public int getSpeed() {
        return speed;
    }

}
