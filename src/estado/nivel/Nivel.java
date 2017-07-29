package estado.nivel;

import entidad.Jugador;
import estado.EstadoJuego;
import estado.EstadoJugador;
import estado.GameOver;
import estado.ManejadorJuego;
import manejador.Teclado;
import mundo.Fondo;
import mundo.Mundo;
import mundo.FondoNegroConTexto;

import java.awt.*;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public abstract class Nivel extends EstadoJuego {

    private static final long CANTIDAD_TIEMPO = 100000;

    private FondoNegroConTexto fondoNegroConTexto;

    private Mundo mundo;
    Jugador jugador;
    Fondo fondo;

    private long tiempo;
    private boolean nivelIniciado;
    private long tiempoIniciado;
    private int nivel;

    private boolean pausado;

    Nivel(ManejadorJuego manejadorJuego, String ubicacionMapa, int nivel) {

        super(manejadorJuego);

        this.nivel = nivel;

        mundo = new Mundo(ubicacionMapa);
        mundo.establecerPosicion(0, 0);

        jugador = new Jugador(mundo);
        jugador.setCantidadVidas(EstadoJugador.getCantidadVidas());

        fondo = new Fondo();
        fondo.setColor(Color.BLACK);
        fondoNegroConTexto = new FondoNegroConTexto(2000, "Nivel " + nivel, 30);

        tiempo = CANTIDAD_TIEMPO;
    }

    public void actualizar() {

        if (!fondoNegroConTexto.yaTermino()) {
            fondoNegroConTexto.actualizar();
        } else if (!nivelIniciado) {
            tiempoIniciado = System.currentTimeMillis();
            nivelIniciado = true;
        } else {
            long tiempoTranscurrido;

            if (pausado) {
                tiempoTranscurrido = CANTIDAD_TIEMPO - tiempo;
                tiempoIniciado = System.currentTimeMillis() - tiempoTranscurrido;
            } else {
                tiempoTranscurrido = System.currentTimeMillis() - tiempoIniciado;
            }

            tiempo = CANTIDAD_TIEMPO - tiempoTranscurrido;

            if (tiempo <= 0) {
                tiempo = CANTIDAD_TIEMPO;
                jugador.disminuirVida();
                nivelIniciado = false;
                jugador.establecerPosicion(0, 0);
                jugador.pararVelocidadX();
                fondoNegroConTexto.reiniciar();
            }

            if (jugador.getCantidadVidas() == 0) {
                manejadorJuego.establecerEstado(new GameOver(manejadorJuego));
            }

            manejarEntrada();

            if (!pausado) {
                jugador.actualizar();
            }
        }

        mundo.establecerPosicion((int) (ANCHO / 2 - jugador.getX() - jugador.getAncho()),
                (int) (ALTO / 2 - jugador.getY() - jugador.getAlto() / 2));
    }

    public void dibujar(Graphics2D g) {

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
        g.drawString("Nivel: " + nivel, 10, 20);
        g.drawString("Tiempo: " + (int) Math.ceil((double) tiempo / 1000), 10, 40);
        g.drawString("Vidas: " + jugador.getCantidadVidas(), 10, 60);

        if (pausado) {
            font = new Font("Showcard Gothic", Font.PLAIN, 40);
            FondoNegroConTexto.dibujarTextoCentrado(g, "PAUSADO", new Rectangle(ANCHO, ALTO), font);
        }
    }

    private void manejarEntrada() {
        jugador.manejarEntrada();
        if (Teclado.esPresionado(Teclado.ESCAPE)) {
            pausado = !pausado;
        }

    }

}
