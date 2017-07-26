package estado.nivel;

import estado.EstadoJugador;
import estado.ManejadorJuego;

import java.awt.*;

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
            manejadorJuego.establecerEstado(nivel3);
        }
    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
    }

}
