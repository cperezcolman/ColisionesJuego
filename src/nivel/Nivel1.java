package nivel;

import java.awt.*;

public class Nivel1 extends Nivel {

    public Nivel1() {
        super("recursos/nivel1.map");
        fondo.setColor(Color.LIGHT_GRAY);
    }

    @Override
    public void actualizar() {

        super.actualizar();

        if (jugador.llegoAlFinal()) {
            cambiarNivel(new Nivel2());
        }
    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
    }

}
