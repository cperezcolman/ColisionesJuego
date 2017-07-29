package main;

import manejador.Teclado;
import estado.ManejadorJuego;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Carlos Perez
 */
public class PanelJuego extends JPanel implements Runnable, KeyListener {

    public static final int ANCHO = 640;
    public static final int ALTO = 480;
    private static final int ANCHO_VENTANA = Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int ALTO_VENTANA = Toolkit.getDefaultToolkit().getScreenSize().height;

    private static final int FPS = 60;

    private Thread hilo;

    private BufferedImage imagen;
    private Graphics2D grafico;

    private ManejadorJuego manejadorNivelesJuego;

    PanelJuego() {

        super();

        setPreferredSize(new Dimension(ANCHO_VENTANA, ALTO_VENTANA));

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

        manejadorNivelesJuego = new ManejadorJuego();

        long retraso = 1000 / FPS;

        long tiempoInicio;
        long tiempoTranscurrido;
        long tiempoPausa;

        while (true) {

            tiempoInicio = System.currentTimeMillis();

            actualizar();
            dibujar();
            mostrarEnPantalla();

            tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
            tiempoPausa = retraso - tiempoTranscurrido;

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
        manejadorNivelesJuego.actualizar();
    }

    private void dibujar() {
        manejadorNivelesJuego.dibujar(grafico);
    }

    private void mostrarEnPantalla() {
        Graphics g2 = getGraphics();
        g2.drawImage(imagen, 0, 0, ANCHO_VENTANA, ALTO_VENTANA, null);
        g2.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        Teclado.establecerEstado(k, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        Teclado.establecerEstado(k, false);
    }
}
