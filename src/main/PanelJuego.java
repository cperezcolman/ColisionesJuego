package main;

import entidad.Entidad;
import mundo.Mundo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class PanelJuego extends JPanel implements Runnable, KeyListener {

    public static final int ANCHO = 640;
    public static final int ALTO = 480;
    private static final int ESCALA = 1;

    private static final int FPS = 60;

    private Thread hilo;

    private BufferedImage imagen;
    private Graphics2D grafico;

    private Mundo mundo;
    private Entidad entidad;

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

        mundo = new Mundo("recursos/mapa.map");
        mundo.establecerPosicion(0, 0);

        entidad = new Entidad(mundo);

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
        entidad.actualizar();
        mundo.establecerPosicion((int) (ANCHO / 2 - entidad.getX() - 32 - entidad.getAncho() / 2),
                (int) (ALTO / 2 - entidad.getY() - entidad.getAlto() / 2));
    }

    private void dibujar() {
        grafico.setColor(Color.LIGHT_GRAY);
        grafico.fillRect(0, 0, ANCHO, ALTO);

        mundo.dibujar(grafico);

        entidad.dibujar(grafico);

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
        if (k == KeyEvent.VK_LEFT) {
            entidad.setIzquierda(true);
            entidad.setDerecha(false);
        }
        if (k == KeyEvent.VK_RIGHT) {
            entidad.setDerecha(true);
            entidad.setIzquierda(false);
        }
        if (k == KeyEvent.VK_UP) {
            entidad.saltar();
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int k = e.getKeyCode();
        if (k == KeyEvent.VK_LEFT) {
            entidad.setIzquierda(false);
        }
        if (k == KeyEvent.VK_RIGHT) {
            entidad.setDerecha(false);
        }
        if (k == KeyEvent.VK_UP) {
            entidad.pararSalto();
        }
    }
}
