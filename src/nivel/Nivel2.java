package nivel;

import entidad.Jugador;
import mundo.Bloque;
import mundo.Fondo;
import mundo.Mundo;

import java.awt.*;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public class Nivel2 extends Nivel{

    public Nivel2() {
        super("recursos/nivel2.map");
        fondo.setColor(Color.GRAY);

    }

    @Override
    public void actualizar() {

        super.actualizar();

        if (jugador.llegoAlFinal()) {
            cambiarNivel(new Nivel3());
        }
    }

    protected void manejarEntrada() {
        jugador.manejarEntrada();
    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
    }


}
