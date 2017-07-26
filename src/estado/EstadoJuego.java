package estado;

import java.awt.*;

public abstract class EstadoJuego {

    ManejadorJuego manejadorJuego;

    EstadoJuego(ManejadorJuego manejadorJuego) {
        this.manejadorJuego = manejadorJuego;
    }

    public abstract void actualizar();

    public abstract void dibujar(Graphics2D g);

}
