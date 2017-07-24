package nivel;

import entidad.Jugador;
import mundo.Fondo;
import mundo.Mundo;
import mundo.FondoNegroConLetra;

import java.awt.*;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public abstract class Nivel {

    public static int contadorNivel = 0;

    FondoNegroConLetra fondoNegroConLetra;
    Mundo mundo;
    Jugador jugador;
    Fondo fondo;

    Nivel(String ubicacionMapa) {

        mundo = new Mundo(ubicacionMapa);
        mundo.establecerPosicion(0, 0);

        jugador = new Jugador(mundo);
        fondo = new Fondo();
        contadorNivel++;
        fondoNegroConLetra = new FondoNegroConLetra(2000, "Nivel " + contadorNivel);
    }

    void cambiarNivel(Nivel nivel) {
        ManejadorNiveles manejadorNivelesNiveles = ManejadorNiveles.getInstance();
        manejadorNivelesNiveles.establecerNivel(nivel);
    }

    protected void actualizar() {

        if (!fondoNegroConLetra.yaTermino()) {
            fondoNegroConLetra.actualizar();
            return;
        }
        manejarEntrada();
        jugador.actualizar();
        mundo.establecerPosicion((int) (ANCHO / 2 - jugador.getX() - 32 - jugador.getAncho() / 2),
                (int) (ALTO / 2 - jugador.getY() - jugador.getAlto() / 2));
    }

    protected void dibujar(Graphics2D g) {
        if (!fondoNegroConLetra.yaTermino()) {
            fondoNegroConLetra.dibujar(g);
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

    protected void manejarEntrada(){
        jugador.manejarEntrada();
    }

}
