package estado.nivel;

import estado.ManejadorJuego;

import java.awt.*;

public class Nivel3 extends Nivel {

    public Nivel3(ManejadorJuego manejadorJuego) {
        super(manejadorJuego, "recursos/nivel3.map");
        fondo.setColor(Color.DARK_GRAY);
    }

    @Override
    public void actualizar() {
        super.actualizar();
        if (jugador.llegoAlFinal()) {
            manejadorJuego.terminarJuego();
        }
    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
    }

}
