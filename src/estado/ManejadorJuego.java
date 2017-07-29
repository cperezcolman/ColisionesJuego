package estado;

import manejador.Teclado;

import java.awt.*;

public class ManejadorJuego {

    private EstadoJuego estadoJuego;

    public ManejadorJuego() {
        EstadoJugador.iniciar();
        estadoJuego = new PantallaInicio(this);
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
