import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Road extends JPanel {
    // Quasi "Liste" für alle Fahrzeuge die drauf sind
    // JPanel wird zum zeichnen benutzt
    final int LANE_HEIGHT = 140;

    public Road() {
        super(); // calls JPanel constr
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
    }
}
