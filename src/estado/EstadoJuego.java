package estado;

import java.awt.*;

public abstract class EstadoJuego {

    protected ManejadorJuego manejadorJuegoJuego;

    protected EstadoJuego() {
        this.manejadorJuegoJuego = ManejadorJuego.getInstance();
    }

    public abstract void actualizar();

    public abstract void dibujar(Graphics2D g);

}
