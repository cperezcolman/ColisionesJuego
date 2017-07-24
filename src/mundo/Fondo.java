package mundo;

import java.awt.*;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public class Fondo {

    private Color color;

    public void setColor(Color color) {
        this.color = color;
    }
    public void dibujar(Graphics2D g) {
        g.setColor(color);
        g.fillRect(0, 0, ANCHO, ALTO);
    }
}
