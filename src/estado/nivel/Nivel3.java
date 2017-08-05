package estado.nivel;

import estado.GameOver;

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

}
