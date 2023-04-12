import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.SelectableChannel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.Border;

public class Traffic implements ActionListener, Runnable {
    JFrame frame = new JFrame("Traffic Simulation");
    Road road = new Road(); // ist ein JPanel Kind
    // Buttondefinierung
    JButton start = new JButton("Start");
    JButton stop = new JButton("Stop");
    JButton semi = new JButton("LKW");
    JButton suv = new JButton("SUV");
    JButton sport = new JButton("Sport");
    // Container bauen
    Container west = new Container();
    Container south = new Container();

    boolean running = false;

    public Traffic() {
        // Draw function der main
        frame.setSize(1000, 550);
        frame.setLayout(new BorderLayout());
        // positionierung Inhalte
        frame.add(road, BorderLayout.CENTER);

        // HInzufügen Start Stop Button am boden
        south.setLayout(new GridLayout(1, 2));
        south.add(start);
        start.addActionListener(this);// Actionlistener für Buttons zufügen
        south.add(stop);
        stop.addActionListener(this);
        frame.add(south, BorderLayout.SOUTH);

        // HInzufügen Auto Button Links
        west.setLayout(new GridLayout(3, 1));
        west.add(suv);
        suv.addActionListener(this); // Actionlistener
        west.add(sport);
        sport.addActionListener(this);
        west.add(semi);
        semi.addActionListener(this);
        frame.add(west, BorderLayout.WEST);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Autos drawen
        Semi foo = new Semi(10, 60);
        Sportscar foo2 = new Sportscar(10, 200);
        SUV foo3 = new SUV(10, 330);
        Sportscar foo4 = new Sportscar(10, 480);

        road.addCar(foo);
        road.addCar(foo2);
        road.addCar(foo3);
        road.addCar(foo4);
        frame.repaint();

    }

    // main methode
    public static void main(String[] args) {
        new Traffic();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(start)) {
            if (running == false) { // gegen Synchro Probleme
                running = true;
                Thread t = new Thread(this);
                t.start(); // startet neues thread und ruft start aus
            }

        }
        if (e.getSource().equals(stop)) {
            running = false;
        }
    }

    @Override
    public void run() {
        while (running == true) {
            road.step();
            frame.repaint();
            try {// Sleep funktion für bessere darstellung
                Thread.sleep(500);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

}
