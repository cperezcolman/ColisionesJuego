package main;

import nivel.ManejadorNiveles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * @author Carlos Perez
 */
public class PanelJuego extends JPanel implements Runnable, KeyListener {

    public static final int ANCHO = 640;
    public static final int ALTO = 480;
    private static final int ESCALA = 1;

    private static final int FPS = 60;

    private Thread hilo;

    private BufferedImage imagen;
    private Graphics2D grafico;

    private ManejadorNiveles manejadorNivelesNiveles;

    PanelJuego() {

        super();

        setPreferredSize(new Dimension(ANCHO * ESCALA, ALTO * ESCALA));

        setFocusable(true);
        requestFocusInWindow();

    }

    @Override
    public void addNotify() {
        super.addNotify();

        if (hilo == null) {
            hilo = new Thread(this);
            addKeyListener(this);
            hilo.start();
        }

    }

    @Override
    public void run() {

        imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
        grafico = (Graphics2D) imagen.getGraphics();

        manejadorNivelesNiveles = ManejadorNiveles.getInstance();

        long tiempoInicio;
        long tiempoTranscurrido;
        long tiempoPausa;

        while (true) {

            tiempoInicio = System.currentTimeMillis();

            actualizar();
            dibujar();
            mostrarEnPantalla();

            tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
            tiempoPausa = 1000 / FPS - tiempoTranscurrido;

            pausar(tiempoPausa);


        }

    }

    private void pausar(long pausa) {
        if (pausa > 0) {
            try {
                Thread.sleep(pausa);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void actualizar() {
        manejadorNivelesNiveles.actualizar();
    }

    private void dibujar() {

        manejadorNivelesNiveles.dibujar(grafico);

    }

    private void mostrarEnPantalla() {
        Graphics g2 = getGraphics();
        g2.drawImage(imagen, 0, 0, ANCHO * ESCALA, ALTO * ESCALA, null);
        g2.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        manejadorNivelesNiveles.keyPressed(k);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        manejadorNivelesNiveles.keyReleased(k);
    }
}
