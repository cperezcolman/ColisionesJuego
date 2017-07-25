package nivel;

import entidad.EstadoJugador;
import entidad.Jugador;
import mundo.Fondo;
import mundo.Mundo;
import mundo.FondoNegroConTexto;

import java.awt.*;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public abstract class Nivel {

    private static final int CANTIDAD_TIEMPO = 10;
    private static int contadorNivel = 0;

    private FondoNegroConTexto fondoNegroConTexto;

    ManejadorJuego manejadorJuego;

    private Mundo mundo;
    Jugador jugador;
    Fondo fondo;

    private int tiempo;
    private boolean iniciado;
    private long tiempoIniciado;

    Nivel(ManejadorJuego manejadorJuego, String ubicacionMapa) {

        this.manejadorJuego = manejadorJuego;

        contadorNivel++;

        mundo = new Mundo(ubicacionMapa);
        mundo.establecerPosicion(0, 0);

        jugador = new Jugador(mundo);
        jugador.setCantidadVidas(EstadoJugador.getCantidadVidas());

        fondo = new Fondo();
        fondoNegroConTexto = new FondoNegroConTexto(2000, "Nivel " + contadorNivel);

        tiempo = CANTIDAD_TIEMPO;
    }

    protected void actualizar() {

        if (!fondoNegroConTexto.yaTermino()) {
            fondoNegroConTexto.actualizar();
            return;
        }

        if (iniciado) {
            long transcurrido = (System.currentTimeMillis() - tiempoIniciado) / 1000;
            tiempo = (int) (CANTIDAD_TIEMPO - transcurrido);
        } else {
            tiempoIniciado = System.currentTimeMillis();
            iniciado = true;
        }

        if (tiempo == 0) {
            tiempo = CANTIDAD_TIEMPO;
            jugador.disminuirVida();
            iniciado = false;
            fondoNegroConTexto = new FondoNegroConTexto(2000, "Nivel " + contadorNivel);
            jugador.establecerPosicion(0, 0);
        }

        if (jugador.getCantidadVidas() == 0) {
            manejadorJuego.terminarJuego();
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
        g.drawString("Nivel: " + contadorNivel, 10, 20);
        g.drawString("Tiempo: " + tiempo, 10, 40);
        g.drawString("Vidas: " + jugador.getCantidadVidas(), 10, 60);
    }

    private void manejarEntrada(){
        jugador.manejarEntrada();
    }
}
