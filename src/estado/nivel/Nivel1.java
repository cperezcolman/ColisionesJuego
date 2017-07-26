package estado.nivel;

import estado.EstadoJugador;
import estado.ManejadorJuego;

import java.awt.*;

public class Nivel1 extends Nivel {

    public Nivel1(ManejadorJuego manejadorJuego) {
        super(manejadorJuego, "recursos/nivel1.map", 1);
    }

    @Override
    public void actualizar() {

        super.actualizar();

        if (jugador.llegoAlFinal()) {
            EstadoJugador.setCantidadVidas(jugador.getCantidadVidas());
            Nivel nivel2 = new Nivel2(manejadorJuego);
            manejadorJuego.establecerEstado(nivel2);
        }
    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
    }

}
