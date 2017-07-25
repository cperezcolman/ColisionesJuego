package nivel;

import entidad.EstadoJugador;
import entidad.Jugador;
import mundo.FondoNegroConTexto;

import java.awt.*;

public class ManejadorJuego {

    private FondoNegroConTexto fondoNegroConTexto;
    private boolean juegoTerminado;
    private Nivel nivelActual;

    public ManejadorJuego() {
        EstadoJugador.iniciar();
        juegoTerminado = false;
        fondoNegroConTexto = new FondoNegroConTexto("Game Over");
        nivelActual = new Nivel1(this);
    }

    void establecerNivel(Nivel nivelNuevo) {
        nivelActual = nivelNuevo;
    }

    public void actualizar() {
        if (!juegoTerminado) {
            nivelActual.actualizar();
        }
    }

    public void dibujar(Graphics2D g) {
        if (juegoTerminado) {
            fondoNegroConTexto.dibujar(g);
            return;
        }
        nivelActual.dibujar(g);
    }

    public void terminarJuego() {
        this.juegoTerminado = true;
    }
}
