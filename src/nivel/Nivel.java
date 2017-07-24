package nivel;

import entidad.Jugador;
import mundo.Fondo;
import mundo.Mundo;
import mundo.FondoNegroConTexto;

import java.awt.*;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public abstract class Nivel {

    private static int contadorNivel = 0;

    private FondoNegroConTexto fondoNegroConTexto;
    Mundo mundo;
    Jugador jugador;
    Fondo fondo;

    Nivel(String ubicacionMapa) {

        mundo = new Mundo(ubicacionMapa);
        mundo.establecerPosicion(0, 0);

        jugador = new Jugador(mundo);
        fondo = new Fondo();
        contadorNivel++;
        fondoNegroConTexto = new FondoNegroConTexto(2000, "Nivel " + contadorNivel);
    }

    void cambiarNivel(Nivel nivel) {
        ManejadorNiveles manejadorNivelesNiveles = ManejadorNiveles.getInstance();
        manejadorNivelesNiveles.establecerNivel(nivel);
    }

    protected void actualizar() {

        if (!fondoNegroConTexto.yaTermino()) {
            fondoNegroConTexto.actualizar();
            return;
        }

        manejarEntrada();
        jugador.actualizar();
        mundo.establecerPosicion((int) (ANCHO / 2 - jugador.getX() - jugador.getAncho()),
                (int) (ALTO / 2 - jugador.getY() - jugador.getAlto() / 2));
    }

    protected void dibujar(Graphics2D g) {
        if (!fondoNegroConTexto.yaTermino()) {
            fondoNegroConTexto.dibujar(g);
            return;
        }
        fondo.dibujar(g);
        mundo.dibujar(g);
        jugador.dibujar(g);
        g.setColor(Color.WHITE);
        Font font = new Font("Showcard Gothic", Font.PLAIN, 15);
        g.setFont(font);
        g.drawString("Nivel " + contadorNivel, 10, 20);
    }

    private void manejarEntrada(){
        jugador.manejarEntrada();
    }

}
