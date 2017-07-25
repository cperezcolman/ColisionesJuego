package entidad;

/**
 * @author Carlos Perez
 */
import manejador.Teclado;
import mundo.Mundo;

import java.awt.*;

public class Jugador extends Entidad {

    private static final double VELOCIDAD_SALTO = -12.0;
    private int cantidadVidas;

    public Jugador(Mundo mundo) {
        super(mundo);

        x = 0;
        y = 0;
        ancho = 32;
        alto = 32;

        xColision = 0;
        yColision = 0;
        anchoColision = ancho;
        altoColision = alto;

        velocidadX = 0;
        aceleracion = 0.2;
        maximaVelocidad = 4.4;

//        cantidadVidas = 5;
    }

    private void saltar() {
        if (enElPiso) {
            velocidadY = VELOCIDAD_SALTO;
            enElPiso = false;
        }
    }

    private void pararSalto() {
        if (velocidadY < (VELOCIDAD_SALTO / 2.0)) {
            velocidadY = (VELOCIDAD_SALTO / 2.0);
        }
    }

    public void manejarEntrada() {
        izquierda = Teclado.estado[Teclado.IZQUIERDA];
        derecha = Teclado.estado[Teclado.DERECHA];
        if (Teclado.estado[Teclado.ARRIBA]) {
            saltar();
        } else {
            pararSalto();
        }
    }

    public void dibujar(Graphics2D g) {

        g.setColor(Color.CYAN);
        g.drawRect((int) (x + mundo.getX() + xColision),
                (int) (y + mundo.getY() + yColision),
                ancho - (ancho - anchoColision),
                alto - (alto - altoColision));

        g.setColor(Color.BLUE);
        g.drawRect((int) (x + mundo.getX()), (int) (y + mundo.getY()), ancho, alto);

    }

    public void disminuirVida() {
        cantidadVidas--;
    }

    public int getCantidadVidas() {
        return cantidadVidas;
    }

    public void establecerPosicion(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setCantidadVidas(int cantidadVidas) {
        this.cantidadVidas = cantidadVidas;
    }
}
