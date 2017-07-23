package nivel;

import entidad.Jugador;
import mundo.Mundo;

import java.awt.*;
import java.awt.event.KeyEvent;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public class Nivel2 extends Nivel{

    private Mundo mundo;
    private Jugador jugador;

    public Nivel2() {

        mundo = new Mundo("recursos/nivel2.map");
        mundo.establecerPosicion(0, 0);

        jugador = new Jugador(mundo);

    }

    @Override
    public void actualizar() {
        jugador.actualizar();
        mundo.establecerPosicion((int) (ANCHO / 2 - jugador.getX() - 32 - jugador.getAncho() / 2),
                (int) (ALTO / 2 - jugador.getY() - jugador.getAlto() / 2));
    }

    @Override
    public void dibujar(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ANCHO, ALTO);
        mundo.dibujar(g);
        jugador.dibujar(g);
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_LEFT) {
            jugador.setIzquierda(true);
            jugador.setDerecha(false);
        }
        if (k == KeyEvent.VK_RIGHT) {
            jugador.setDerecha(true);
            jugador.setIzquierda(false);
        }
        if (k == KeyEvent.VK_UP) {
            jugador.saltar();
        }
    }

    @Override
    public void keyReleased(int k) {
        if (k == KeyEvent.VK_LEFT) {
            jugador.setIzquierda(false);
        }
        if (k == KeyEvent.VK_RIGHT) {
            jugador.setDerecha(false);
        }
        if (k == KeyEvent.VK_UP) {
            jugador.pararSalto();
        }
    }

}
