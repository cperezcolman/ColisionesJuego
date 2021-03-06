package entidad;

import manejador.Teclado;
import mundo.Mundo;

import java.awt.event.KeyEvent;

/**
 * @author Carlos Perez
 */
public class Jugador extends EntidadBasica {

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
        izquierda = Teclado.getEstado(KeyEvent.VK_LEFT);
        derecha = Teclado.getEstado(KeyEvent.VK_RIGHT);
        if (Teclado.getEstado(KeyEvent.VK_UP)) {
            saltar();
        } else {
            pararSalto();
        }
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
