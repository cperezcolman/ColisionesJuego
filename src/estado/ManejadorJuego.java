package estado;

import mundo.FondoNegroConTexto;

import java.awt.*;

public class ManejadorJuego {

    private FondoNegroConTexto fondoNegroConTexto;
    private boolean juegoTerminado;
    private EstadoJuego estadoJuego;

    public ManejadorJuego() {
        EstadoJugador.iniciar();
        juegoTerminado = false;
        fondoNegroConTexto = new FondoNegroConTexto("Game Over");
        estadoJuego = new Nivel1(this);
    }

    void establecerEstado(EstadoJuego nuevoEstado) {
        estadoJuego = nuevoEstado;
    }

    public void actualizar() {
        if (!juegoTerminado) {
            estadoJuego.actualizar();
        }
    }

    public void dibujar(Graphics2D g) {
        if (juegoTerminado) {
            fondoNegroConTexto.dibujar(g);
            return;
        }
        estadoJuego.dibujar(g);
    }

    void terminarJuego() {
        this.juegoTerminado = true;
    }
}
