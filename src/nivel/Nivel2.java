package nivel;

import entidad.EstadoJugador;
import entidad.Jugador;
import mundo.Bloque;
import mundo.Fondo;
import mundo.Mundo;

import java.awt.*;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public class Nivel2 extends Nivel{

    public Nivel2(ManejadorJuego manejadorJuego) {
        super(manejadorJuego, "recursos/nivel2.map");
        fondo.setColor(Color.GRAY);

    }

    @Override
    public void actualizar() {

        super.actualizar();

        if (jugador.llegoAlFinal()) {
            EstadoJugador.setCantidadVidas(jugador.getCantidadVidas());
            Nivel nivel3 = new Nivel3(manejadorJuego);
            manejadorJuego.establecerNivel(nivel3);
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
