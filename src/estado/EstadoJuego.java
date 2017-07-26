package estado;

import java.awt.*;

public abstract class EstadoJuego {

    protected ManejadorJuego manejadorJuego;

    protected EstadoJuego(ManejadorJuego manejadorJuego) {
        this.manejadorJuego = manejadorJuego;
    }

    public abstract void actualizar();

    public abstract void dibujar(Graphics2D g);

}
