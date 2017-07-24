package nivel;

import mundo.Bloque;

import java.awt.*;

public class Nivel2 extends Nivel{

    public Nivel2() {
        super("recursos/nivel2.map");
        fondo.setColor(Color.GRAY);
    }

    @Override
    public void actualizar() {

        super.actualizar();

        if (jugador.getX() >= (mundo.getBloques()[0].length * Bloque.TAMANO) - Bloque.TAMANO) {
            cambiarNivel(new Nivel3());
        }
    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
    }


}
