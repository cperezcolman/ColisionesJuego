package nivel;

import mundo.Bloque;

import java.awt.*;

public class Nivel3 extends Nivel {

    public Nivel3() {
        super("recursos/nivel3.map");
        fondo.setColor(Color.DARK_GRAY);
    }

    @Override
    public void actualizar() {
        super.actualizar();
        if (jugador.getX() >= (mundo.getBloques()[0].length * Bloque.TAMANO) - Bloque.TAMANO) {
            ManejadorNiveles manejadorNivelesNiveles = ManejadorNiveles.getInstance();
            manejadorNivelesNiveles.terminarJuego();
        }
    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
    }

}
