package nivel;

import entidad.Jugador;
import mundo.Bloque;
import mundo.Mundo;

import java.awt.*;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public class Nivel2 extends Nivel{

    private Mundo mundo;
    private Jugador jugador;

    public Nivel2() {

        mundo = new Mundo("recursos/nivel2.map");
        mundo.establecerPosicion(0, 0);

        jugador = new Jugador(mundo);

    }

    @Override
    public void actualizar() {
        manejarEntrada();
        jugador.actualizar();
        mundo.establecerPosicion((int) (ANCHO / 2 - jugador.getX() - 32 - jugador.getAncho() / 2),
                (int) (ALTO / 2 - jugador.getY() - jugador.getAlto() / 2));

        if (jugador.getX() >= (mundo.getBloques()[0].length * Bloque.TAMANO) - Bloque.TAMANO) {
            cambiarNivel(new Nivel3());
        }
    }

    private void manejarEntrada() {
        jugador.manejarEntrada();
    }

    @Override
    public void dibujar(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ANCHO, ALTO);
        mundo.dibujar(g);
        jugador.dibujar(g);
    }


}
