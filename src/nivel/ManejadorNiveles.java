package nivel;

import java.awt.*;

public class ManejadorNiveles {

    private Nivel nivelActual;
    private static final ManejadorNiveles instancia = new ManejadorNiveles();

    public static ManejadorNiveles getInstance() {
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
}
