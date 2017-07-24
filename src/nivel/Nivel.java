package nivel;

import entidad.Jugador;
import mundo.Fondo;
import mundo.Mundo;

import java.awt.*;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public abstract class Nivel {

    Mundo mundo;
    Jugador jugador;
    Fondo fondo;

    public Nivel(String ubicacionMapa) {

        mundo = new Mundo(ubicacionMapa);
        mundo.establecerPosicion(0, 0);

        jugador = new Jugador(mundo);
        fondo = new Fondo();
    }

    void cambiarNivel(Nivel nivel) {
        ManejadorNiveles manejadorNivelesNiveles = ManejadorNiveles.getInstance();
        manejadorNivelesNiveles.establecerNivel(nivel);
    }

    protected void actualizar() {
        manejarEntrada();
        jugador.actualizar();
        mundo.establecerPosicion((int) (ANCHO / 2 - jugador.getX() - 32 - jugador.getAncho() / 2),
                (int) (ALTO / 2 - jugador.getY() - jugador.getAlto() / 2));

    }

    protected void dibujar(Graphics2D g) {
        fondo.dibujar(g);
        mundo.dibujar(g);
        jugador.dibujar(g);
    }

    private void manejarEntrada(){
        jugador.manejarEntrada();
    }

}
