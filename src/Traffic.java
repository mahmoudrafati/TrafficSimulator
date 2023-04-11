import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Traffic {
    JFrame frame = new JFrame("Traffic Simulation");
    Road road = new Road(); // ist ein JPanel Kind

    public Traffic() {
        // Draw function der main
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.add(road, BorderLayout.CENTER);
        // Ende JFrame

        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // main methode
    public static void main(String[] args) {
        new Traffic();
    }
}
