import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.SelectableChannel;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
    JButton löschen = new JButton("Löschen");
    JLabel durchsatz = new JLabel("Durchsatz: ");

    // Container bauen
    Container west = new Container();
    Container south = new Container();

    boolean running = false;
    long startTime = 0;
    int carCount = 0;

    public Traffic() {
        // Draw function der main
        frame.setSize(1000, 550);
        frame.setLayout(new BorderLayout());
        // positionierung Inhalte
        frame.add(road, BorderLayout.CENTER);

        // HInzufügen Start Stop Button am boden
        south.setLayout(new GridLayout(2, 2));
        south.add(start);
        start.addActionListener(this);// Actionlistener für Buttons zufügen
        south.add(stop);
        stop.addActionListener(this);
        south.add(löschen);
        löschen.addActionListener(this);
        south.add(durchsatz);
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

        /*
         * Autos drawen
         * Semi foo = new Semi(10, 60);
         * Sportscar foo2 = new Sportscar(10, 200);
         * SUV foo3 = new SUV(10, 330);
         * Sportscar foo4 = new Sportscar(10, 480);
         * 
         * road.addCar(foo);
         * road.addCar(foo2);
         * road.addCar(foo3);
         * road.addCar(foo4);
         */
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
                road.resetCarCount(); // setzt anzahl auf 0 bei neustart
                startTime = System.currentTimeMillis(); // resettet Zeit auf aktuelle
                Thread t = new Thread(this);
                t.start(); // startet neues thread und ruft start aus
            }
        }
        if (e.getSource().equals(löschen)) {
            road.cars.clear();
            road.resetCarCount();
        }
        if (e.getSource().equals(stop)) {
            running = false;
        }

        if (e.getSource().equals(semi)) {
            Semi semi = new Semi(0, 30);
            road.addCar(semi);
            for (int x = 0; x < road.ROAD_WIDTH; x += 20) {// setzen neu erstelltes semi an 0
                for (int y = 40; y < 600; y += 120) {
                    // man testet an deen jeweiligen y-positionen
                    // wenn es keine kollision gibt, wird
                    semi.setX(x); // setze x und y als passenden wert
                    semi.setY(y); // rausgefahren
                    if (road.collision(x, y, semi) == false) {
                        frame.repaint();
                        return;
                    }
                }
            }
        }
        if (e.getSource().equals(sport)) {
            Sportscar sportscar = new Sportscar(0, 30);
            road.addCar(sportscar);
            for (int x = 0; x < road.ROAD_WIDTH; x += 20) {// setzen neu erstelltes sport an 0
                for (int y = 40; y < 600; y += 120) {
                    // man testet an deen jeweiligen y-positionen
                    // wenn es keine kollision gibt, wird
                    sportscar.setX(x); // setze x und y als passenden wert
                    sportscar.setY(y); // rausgefahren
                    if (road.collision(x, y, sportscar) == false) {
                        frame.repaint();
                        return;
                    }
                }
            }
        }
        if (e.getSource().equals(suv)) {
            SUV suv = new SUV(0, 30);
            road.addCar(suv);
            for (int x = 0; x < road.ROAD_WIDTH; x += 20) {// setzen neu erstelltes suv an 0
                for (int y = 40; y < 600; y += 120) {
                    // man testet an deen jeweiligen y-positionen
                    // wenn es keine kollision gibt, wird
                    suv.setX(x); // setze x und y als passenden wert
                    suv.setY(y); // rausgefahren
                    if (road.collision(x, y, suv) == false) {
                        frame.repaint();
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void run() {
        while (running == true) {
            road.step();
            carCount = road.getCarcount();
            double throughput = carCount / (1000 * (double) (System.currentTimeMillis() - startTime)); // Rechnet den
                                                                                                       // Durchsatz
            // mit anzahl an Autos die
            // durchfahren die in der
            // Zeit durchfahren
            durchsatz.setText("Durchsatz pro sek: " + throughput); // Ausgabe im Label
            frame.repaint();
            try {// Sleep funktion für bessere darstellung
                Thread.sleep(100);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }

}
