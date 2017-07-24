package main;

import javax.swing.*;
import java.awt.*;

/**
 * @author Carlos Perez
 */
public class Main {

    public static void main(String[] args) {

        JFrame ventana = new JFrame("Colisiones Juego");
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setContentPane(new PanelJuego());
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);

    }
}
