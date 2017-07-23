package entidad;

import mundo.Bloque;
import mundo.Mundo;

import java.awt.*;

/**
 * @author Carlos Perez
 */
public class Entidad extends EntidadBasica {

//    //constantes fisica
    private static final double GRAVEDAD = 0.5;

    //mover a la izquierda o derecha
    private boolean izquierda;
    private boolean derecha;

    //limite de la figura
    private int limiteIzquierda;
    private int limiteDerecha;
    private int limiteArriba;
    private int limiteAbajo;

    //fisica
    double velocidadX;
    double aceleracion;
    double maximaVelocidad;
    double velocidadY;

    boolean enElPiso;
    private boolean mirandoHaciaLaDerecha;

    Mundo mundo;

    public Entidad(Mundo mundo) {
        this.mundo = mundo;
    }

    public void actualizar() {

        if (izquierda) {
            mirandoHaciaLaDerecha = false;
            velocidadX -= aceleracion;
            if (velocidadX < -maximaVelocidad) {
                velocidadX = -maximaVelocidad;
            }
        } else if (derecha) {
            mirandoHaciaLaDerecha = true;
            velocidadX += aceleracion;
            if (velocidadX > maximaVelocidad) {
                velocidadX = maximaVelocidad;
            }
        } else {
            if (mirandoHaciaLaDerecha) {
                velocidadX -= aceleracion;
                if (velocidadX < 0) {
                    velocidadX = 0;
                }
            } else {
                velocidadX += aceleracion;
                if (velocidadX > 0) {
                    velocidadX = 0;
                }
            }
        }

        x = velocidadX == 0 ? (int) x : x + velocidadX;

        verificarColisiones();

        if ((x + xColision) < limiteIzquierda) {
            x = limiteIzquierda - xColision;
            velocidadX = 0;
        }

        if ((x + xColision + anchoColision) > limiteDerecha) {
            x = limiteDerecha - (xColision + anchoColision);
            velocidadX = 0;
        }

        velocidadY += GRAVEDAD;
        y += velocidadY;

        verificarColisiones();

        if ((y + yColision) < limiteArriba) {
            y = limiteArriba - yColision;
            velocidadY = 0;
        }

        if ((y + yColision + altoColision) > limiteAbajo) {
            y = limiteAbajo - (yColision + altoColision);
            velocidadY = 0;
            enElPiso = true;
        } else {
            enElPiso = false;
        }

    }

    private void verificarColisiones() {

        double bordeColisionIzquierda = (x + xColision);
        double bordeColisionDerecha = (x + xColision + anchoColision);
        double bordeColisionArriba = (y + yColision);
        double bordeColisionAbajo = (y + yColision + altoColision);

        int bloqueInterseccionIzquierda = (int) (bordeColisionIzquierda / Bloque.TAMANO);
        int bloqueInterseccionDerecha = (int) Math.ceil(bordeColisionDerecha / Bloque.TAMANO) - 1;
        int bloqueInterseccionArriba = (int) (bordeColisionArriba / Bloque.TAMANO);
        int bloqueInterseccionAbajo = (int) Math.ceil(bordeColisionAbajo / Bloque.TAMANO) - 1;

        limiteArriba = 0;
        limiteAbajo = mundo.getBloques().length * Bloque.TAMANO;
        limiteIzquierda = 0;
        limiteDerecha = mundo.getBloques()[0].length * Bloque.TAMANO;


        Bloque bloque;

        for (int i = bloqueInterseccionArriba; i <= bloqueInterseccionAbajo; i++) {

            if (i < 0 || i >= mundo.getBloques().length) {
                continue;
            }

            for (int j = bloqueInterseccionIzquierda; j <= bloqueInterseccionDerecha; j++) {

                if (j < 0 || j >= mundo.getBloques()[i].length) {
                    continue;
                }

                bloque = mundo.getBloques()[i][j];

                if (bloque.isBloqueado()) {

                    if (j == bloqueInterseccionIzquierda && bordeColisionIzquierda < (bloque.getX() + Bloque.TAMANO)
                            && bordeColisionDerecha > (bloque.getX() + Bloque.TAMANO)) {
                        limiteIzquierda = bloque.getX() + Bloque.TAMANO;
                    }

                    if (j == bloqueInterseccionDerecha && bordeColisionDerecha > bloque.getX()
                            && bordeColisionIzquierda < bloque.getX()) {
                        limiteDerecha = bloque.getX();
                    }

                    if (i == bloqueInterseccionArriba && bordeColisionArriba < (bloque.getY() + Bloque.TAMANO)
                            && bordeColisionAbajo > (bloque.getY() + Bloque.TAMANO)) {
                        limiteArriba = bloque.getY() + Bloque.TAMANO;
                    }

                    if (i == bloqueInterseccionAbajo && bordeColisionAbajo > bloque.getY()
                            && bordeColisionArriba < bloque.getY()) {
                        limiteAbajo = bloque.getY();
                    }
                }

            }
        }
    }

    public void setIzquierda(boolean izquierda) {
        this.izquierda = izquierda;
    }

    public void setDerecha(boolean derecha) {
        this.derecha = derecha;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}
