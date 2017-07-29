package main;

import estado.ManejadorJuego;
import estado.PantallaInicio;
import manejador.Teclado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * @author Carlos Perez
 */
public class PanelJuego extends JPanel implements Runnable, KeyListener, FocusListener {

    public static boolean focoPerdido = false;

    public static final int ANCHO = 640;
    public static final int ALTO = 480;
    //    private static final int ANCHO_VENTANA = Toolkit.getDefaultToolkit().getScreenSize().width;
//    private static final int ALTO_VENTANA = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static final int ANCHO_VENTANA = ANCHO;
    private static final int ALTO_VENTANA = ALTO;

    private static final int FPS = 60;

    private Thread hilo;

    private BufferedImage imagen;
    private Graphics2D grafico;

    public ManejadorJuego manejadorJuegoJuego;

    PanelJuego() {

        super();

        setPreferredSize(new Dimension(ANCHO_VENTANA, ALTO_VENTANA));

        setFocusable(true);
        requestFocusInWindow();

    }

    @Override
    public void focusLost(FocusEvent fe) {
        focoPerdido = true;
        Teclado.forzarKeyReleased();
    }

    @Override
    public void focusGained(FocusEvent e) {
        focoPerdido = false;
    }

    @Override
    public void addNotify() {
        super.addNotify();

        if (hilo == null) {
            hilo = new Thread(this);
            addKeyListener(this);
            addFocusListener(this);
            hilo.start();
        }

    }

    @Override
    public void run() {

        imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
        grafico = (Graphics2D) imagen.getGraphics();

        manejadorJuegoJuego = ManejadorJuego.getInstance();
        manejadorJuegoJuego.establecerEstado(new PantallaInicio());

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
        manejadorJuegoJuego.actualizar();
    }

    private void dibujar() {
        manejadorJuegoJuego.dibujar(grafico);
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
