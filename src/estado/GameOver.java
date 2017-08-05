package estado;

import estado.nivel.Nivel;
import estado.nivel.Nivel1;
import util.Util;

import java.awt.*;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public class GameOver extends EstadoJuego {

    private ListaOpciones listaOpciones;

    public GameOver() {
        super();
        Font font = new Font("Showcard Gothic", Font.PLAIN, 20);
        String[] opciones = {"Si", "No"};
        listaOpciones = new ListaOpciones(opciones, font, 320, 20);
    }

    @Override
    public void actualizar() {

        listaOpciones.actualizar();

        if (listaOpciones.isOpcionSeleccionada()) {
            seleccionar();
        }
    }

    private void seleccionar() {

        int opcionSeleccionada = listaOpciones.getSeleccionActual();

        if (opcionSeleccionada == 0) {
            EstadoJugador.iniciar();
            Nivel nivel1 = new Nivel1();
            manejadorJuegoJuego.establecerEstado(nivel1);
        } else if (opcionSeleccionada == 1) {
            PantallaInicio pantallaInicio = new PantallaInicio();
            manejadorJuegoJuego.establecerEstado(pantallaInicio);
        }

    }

    @Override
    public void dibujar(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ANCHO, ALTO);
        g.setColor(Color.GREEN);
        Font font = new Font("Showcard Gothic", Font.PLAIN, 40);
        g.setFont(font);
        Util.dibujarTextoCentrado(g, "Game Over", ANCHO, ALTO, font);

        dibujarOpciones(g);
    }

    private void dibujarOpciones(Graphics2D g) {

        Font font = new Font("Showcard Gothic", Font.PLAIN, 20);

        g.setColor(Color.WHITE);
        Util.dibujarTextoCentradoHorizontalmente(g, "¿Jugar de Nuevo?", font, ANCHO, 300);

        listaOpciones.dibujar(g);
    }
}
