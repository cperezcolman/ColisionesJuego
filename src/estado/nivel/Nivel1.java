package estado.nivel;

import estado.EstadoJugador;

import java.awt.*;

public class Nivel1 extends Nivel {

    public Nivel1() {
        super("recursos/nivel1.map", 1);
    }

    @Override
    public void actualizar() {

        super.actualizar();

        if (jugador.llegoAlFinal()) {
            EstadoJugador.setCantidadVidas(jugador.getCantidadVidas());
            Nivel nivel2 = new Nivel2();
            manejadorJuegoJuego.establecerEstado(nivel2);
        }
    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
    }

}
