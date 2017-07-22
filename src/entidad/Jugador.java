package entidad;

import mundo.Mundo;

public class Jugador extends Entidad{

    private static final double VELOCIDAD_SALTO = -12.0;

    public Jugador(Mundo mundo) {
        super(mundo);
    }

    public void saltar() {
        if (enElPiso) {
            velocidadY = VELOCIDAD_SALTO;
            enElPiso = false;
        }
    }

    public void pararSalto() {
        if (velocidadY < (VELOCIDAD_SALTO / 2.0)) {
            velocidadY = (VELOCIDAD_SALTO / 2.0);
        }
    }
}
