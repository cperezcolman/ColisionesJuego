package nivel;

import java.awt.*;

public class ManejadorNiveles {

    private Nivel nivelActual;
    private static ManejadorNiveles instancia = null;

    public static ManejadorNiveles getInstance() {
        if (instancia == null) {
            instancia = new ManejadorNiveles();
        }
        return instancia;
    }

    private ManejadorNiveles() {
        nivelActual = new Nivel1();
    }

    void establecerNivel(Nivel nivelNuevo) {
        this.nivelActual = nivelNuevo;
    }

    public void actualizar() {
        nivelActual.actualizar();
    }

    public void dibujar(Graphics2D g) {
        nivelActual.dibujar(g);
    }

    public void keyPressed(int k) {
        nivelActual.keyPressed(k);
    }

    public void keyReleased(int k) {
        nivelActual.keyReleased(k);
    }
}
