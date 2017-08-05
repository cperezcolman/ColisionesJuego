package estado;

import manejador.Teclado;
import util.Util;

import java.awt.*;
import java.awt.event.KeyEvent;

import static main.PanelJuego.ANCHO;

public class ListaOpciones {

    private String[] opciones;
    private Font fuente;
    private int yInicio;
    private int separacion;
    private int seleccionActual;
    private boolean opcionSeleccionada;

    public ListaOpciones(String[] opciones, Font fuente, int yInicio, int separacion) {
        this.opciones = opciones;
        this.fuente = fuente;
        this.yInicio = yInicio;
        this.separacion = separacion;

    }

    public void actualizar() {

        opcionSeleccionada = Teclado.esPresionado(KeyEvent.VK_ENTER);

        if (Teclado.esPresionado(KeyEvent.VK_UP)) {
            if (seleccionActual > 0) {
                seleccionActual--;
            } else {
                seleccionActual = opciones.length - 1;
            }
        }

        if (Teclado.esPresionado(KeyEvent.VK_DOWN)) {
            if (seleccionActual < opciones.length - 1) {
                seleccionActual++;
            } else {
                seleccionActual = 0;
            }
        }
    }

    public void dibujar(Graphics2D g) {

        int posicionY = yInicio;

        for (int i = 0; i < opciones.length; i++) {

            String opcion = opciones[i];

            if (seleccionActual == i) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.GRAY);
            }

            Util.dibujarTextoCentradoHorizontalmente(g, opcion, fuente, ANCHO, posicionY += separacion);
        }
    }

    public int getSeleccionActual() {
        return seleccionActual;
    }

    public boolean isOpcionSeleccionada() {
        return opcionSeleccionada;
    }
}
