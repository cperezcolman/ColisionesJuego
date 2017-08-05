package estado.nivel;

import estado.EstadoJugador;

public class Nivel2 extends Nivel {

    public Nivel2() {
        super("recursos/nivel2.map", 2);

    }

    @Override
    public void actualizar() {

        super.actualizar();

        if (jugador.llegoAlFinal()) {
            EstadoJugador.setCantidadVidas(jugador.getCantidadVidas());
            Nivel nivel3 = new Nivel3();
            manejadorJuegoJuego.establecerEstado(nivel3);
        }
    }

}
