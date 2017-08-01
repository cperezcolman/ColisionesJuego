package estado.nivel;

import entidad.Jugador;
import estado.*;
import main.PanelJuego;
import manejador.Teclado;
import mundo.Fondo;
import mundo.FondoNegroConTexto;
import mundo.Mundo;

import java.awt.*;
import java.awt.event.KeyEvent;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public abstract class Nivel extends EstadoJuego {

    private static final long CANTIDAD_TIEMPO = 100000;

    private FondoNegroConTexto fondoNegroConTexto;
    private ListaOpciones listaOpciones;

    private Mundo mundo;
    Jugador jugador;
    Fondo fondo;

    private long tiempo;
    private boolean nivelIniciado;
    private long tiempoIniciado;
    private int nivel;

    private boolean pausado;

    Nivel(String ubicacionMapa, int nivel) {

        super();

        this.nivel = nivel;

        mundo = new Mundo(ubicacionMapa);
        mundo.establecerPosicion(0, 0);

        jugador = new Jugador(mundo);
        jugador.setCantidadVidas(EstadoJugador.getCantidadVidas());

        fondo = new Fondo();
        fondo.setColor(new Color(18, 65, 121));
        fondoNegroConTexto = new FondoNegroConTexto(2000, "Nivel " + nivel, 30);

        tiempo = CANTIDAD_TIEMPO;

        Font font = new Font("Showcard Gothic", Font.PLAIN, 20);
        String[] opciones = {"Continuar", "Volver a Pantalla de Inicio"};
        listaOpciones = new ListaOpciones(opciones, font, 300, 40);
    }

    public void actualizar() {

        if (PanelJuego.focoPerdido) {
            pausado = true;
        }

        long tiempoTranscurrido;

        if (!fondoNegroConTexto.yaTermino()) {

            fondoNegroConTexto.actualizar();

        } else {

            manejarEntrada();

            if (!nivelIniciado) {

                tiempoIniciado = System.currentTimeMillis();
                nivelIniciado = true;
                tiempo = CANTIDAD_TIEMPO;

            } else if (pausado) {

                tiempoTranscurrido = CANTIDAD_TIEMPO - tiempo;
                tiempoIniciado = System.currentTimeMillis() - tiempoTranscurrido;

                listaOpciones.actualizar();

                if (listaOpciones.isOpcionSeleccionada()) {
                    seleccionar();
                }

            } else {

                tiempoTranscurrido = System.currentTimeMillis() - tiempoIniciado;
                jugador.actualizar();

                tiempo = CANTIDAD_TIEMPO - tiempoTranscurrido;

                if (tiempo <= 0) {
                    jugador.disminuirVida();
                    jugador.establecerPosicion(0, 0);
                    jugador.pararVelocidadX();
                    fondoNegroConTexto.reiniciar();
                    nivelIniciado = false;
                }

                if (jugador.getCantidadVidas() == 0) {
                    manejadorJuegoJuego.establecerEstado(new GameOver());
                }
            }
        }

        mundo.establecerPosicion((int) (ANCHO / 2 - jugador.getX() - jugador.getAncho()),
                (int) (ALTO / 2 - jugador.getY() - jugador.getAlto()));
    }

    private void seleccionar() {

        int opcionSeleccionada = listaOpciones.getSeleccionActual();

        if (opcionSeleccionada == 0) {
            pausado = false;
        } else if (opcionSeleccionada == 1) {
            PantallaInicio pantallaInicio = new PantallaInicio();
            manejadorJuegoJuego.establecerEstado(pantallaInicio);
        }

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
            FondoNegroConTexto.dibujarTextoCentrado(g, "PAUSADO", ANCHO, ALTO, font);
            listaOpciones.dibujar(g);
        }
    }

    private void manejarEntrada() {
        if (!pausado) {
            jugador.manejarEntrada();
        }
        if (Teclado.esPresionado(KeyEvent.VK_ESCAPE)) {
            pausado = !pausado;
        }

    }

}
