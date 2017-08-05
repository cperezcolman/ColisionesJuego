package entidad;

import mundo.Mundo;

import java.awt.*;

/**
 * @author Carlos Perez
 */
public class Entidad {

    Mundo mundo;

    //ubicacion y tamano
    double x;
    double y;
    int ancho;
    int alto;

    //ubicacion y tamano colision
    double xColision;
    double yColision;
    int anchoColision;
    int altoColision;

    public void dibujar(Graphics2D g) {

        g.setColor(Color.WHITE);
        g.drawRect((int) (x + mundo.getX() + xColision),
                (int) (y + mundo.getY() + yColision),
                ancho - (ancho - anchoColision),
                alto - (alto - altoColision));

        g.setColor(Color.GREEN);
        g.drawRect((int) (x + mundo.getX()), (int) (y + mundo.getY()), ancho, alto);

    }

}
