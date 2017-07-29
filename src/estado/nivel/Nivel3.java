package estado.nivel;

import estado.GameOver;

import java.awt.*;

public class Nivel3 extends Nivel {

    public Nivel3() {
        super("recursos/nivel3.map", 3);
    }

    @Override
    public void actualizar() {
        super.actualizar();
        if (jugador.llegoAlFinal()) {
            manejadorJuegoJuego.establecerEstado(new GameOver());
        }
    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
    }

}
