package nivel;

import mundo.FondoNegroConTexto;

import java.awt.*;

public class ManejadorNiveles {

    private FondoNegroConTexto fondoNegroConTexto;
    private boolean terminado;
    private Nivel nivelActual;
    private static final ManejadorNiveles instancia = new ManejadorNiveles();

    public static ManejadorNiveles getInstance() {
        return instancia;
    }

    private ManejadorNiveles() {
        nivelActual = new Nivel1();
        terminado = false;
        fondoNegroConTexto = new FondoNegroConTexto("Game Over");
    }

    void establecerNivel(Nivel nivelNuevo) {
        this.nivelActual = nivelNuevo;
    }

    public void actualizar() {
        if (terminado) {
            return;
        }
        nivelActual.actualizar();

    }

    public void dibujar(Graphics2D g) {
        if (terminado) {
            fondoNegroConTexto.dibujar(g);
            return;
        }
        nivelActual.dibujar(g);
    }

    public void setTerminado(boolean terminado) {
        this.terminado = terminado;
    }
}
