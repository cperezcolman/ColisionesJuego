package nivel;

import java.awt.*;

public abstract class Nivel {

    void cambiarNivel(Nivel nivel) {
        ManejadorNiveles manejadorNivelesNiveles = ManejadorNiveles.getInstance();
        manejadorNivelesNiveles.establecerNivel(nivel);
    }

    abstract void actualizar();

    abstract void dibujar(Graphics2D g);

}
