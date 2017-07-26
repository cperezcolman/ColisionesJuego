package estado.nivel;

import estado.GameOver;
import estado.ManejadorJuego;

import java.awt.*;

public class Nivel3 extends Nivel {

    public Nivel3(ManejadorJuego manejadorJuego) {
        super(manejadorJuego, "recursos/nivel3.map", 3);
    }

    @Override
    public void actualizar() {
        super.actualizar();
        if (jugador.llegoAlFinal()) {
            manejadorJuego.establecerEstado(new GameOver(manejadorJuego));
        }
    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
    }

}
