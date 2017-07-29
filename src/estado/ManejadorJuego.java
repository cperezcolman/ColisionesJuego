package estado;

import manejador.Teclado;

import java.awt.*;

public class ManejadorJuego {

    private static ManejadorJuego instancia = new ManejadorJuego();
    private EstadoJuego estadoJuego;

    public static ManejadorJuego getInstance() {
        return instancia;
    }

    private ManejadorJuego() {
        EstadoJugador.iniciar();
    }

    public void establecerEstado(EstadoJuego nuevoEstado) {
        estadoJuego = nuevoEstado;
    }

    public void actualizar() {
        estadoJuego.actualizar();
        Teclado.actualizar();
    }

    public void dibujar(Graphics2D g) {
        estadoJuego.dibujar(g);
    }
}
