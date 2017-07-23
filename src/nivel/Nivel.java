package nivel;

import java.awt.*;

public abstract class Nivel {

    ManejadorNiveles manejadorNivelesNiveles;

    abstract void actualizar();

    abstract void dibujar(Graphics2D g);

    abstract void keyPressed(int k);

    abstract void keyReleased(int k);

}
