package nivel;

import entidad.EstadoJugador;

import java.awt.*;

public class Nivel1 extends Nivel {

    public Nivel1(ManejadorJuego manejadorJuego) {
        super(manejadorJuego, "recursos/nivel1.map");
        fondo.setColor(Color.LIGHT_GRAY);
    }

    @Override
    public void actualizar() {

        super.actualizar();

        if (jugador.llegoAlFinal()) {
            EstadoJugador.setCantidadVidas(jugador.getCantidadVidas());
            Nivel nivel2 = new Nivel2(manejadorJuego);
            manejadorJuego.establecerNivel(nivel2);
        }
    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
    }

}
