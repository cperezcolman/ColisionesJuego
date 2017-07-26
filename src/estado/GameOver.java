package estado;

import estado.nivel.Nivel;
import estado.nivel.Nivel1;
import manejador.Teclado;
import mundo.FondoNegroConTexto;

import java.awt.*;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public class GameOver extends EstadoJuego {

    private int seleccionActual;
    private String[] opciones = {"Si", "No"};

    public GameOver(ManejadorJuego manejadorJuego) {
        super(manejadorJuego);
    }

    @Override
    public void actualizar() {

        if (Teclado.estado[Teclado.ENTER]) {
            seleccionar();
        }
        if (Teclado.esPresionado(Teclado.ARRIBA)) {
            if (seleccionActual > 0) {
                seleccionActual--;
            } else {
                seleccionActual = opciones.length - 1;
            }
        }
        if (Teclado.esPresionado(Teclado.ABAJO)) {
            if (seleccionActual < opciones.length - 1) {
                seleccionActual++;
            } else {
                seleccionActual = 0;
            }
        }
    }

    private void seleccionar() {
        if (seleccionActual == 0) {
            EstadoJugador.iniciar();
            Nivel nivel1 = new Nivel1(manejadorJuego);
            manejadorJuego.establecerEstado(nivel1);
        } else if (seleccionActual == 1) {
            System.exit(0);
        }

    }

    @Override
    public void dibujar(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, ANCHO, ALTO);
        g.setColor(Color.GREEN);
        Font font = new Font("Showcard Gothic", Font.PLAIN, 40);
        g.setFont(font);
        FondoNegroConTexto.dibujarTextoCentrado(g, "Game Over", new Rectangle(ANCHO, ALTO), font);

        dibujarOpciones(g);
    }

    private void dibujarOpciones(Graphics2D g) {

        Font font = new Font("Showcard Gothic", Font.PLAIN, 20);

        g.setColor(Color.WHITE);
        FondoNegroConTexto.dibujarTextoCentradoHorizontalmente(g, "¿Jugar de Nuevo?", font, ANCHO, 300);

        int posicionY = 320;

        for (int i = 0; i < opciones.length; i++) {
            String opcion = opciones[i];
            if (seleccionActual == i) {
                g.setColor(Color.WHITE);
            } else {
                g.setColor(Color.GRAY);
            }

            FondoNegroConTexto.dibujarTextoCentradoHorizontalmente(g, opcion, font, ANCHO, posicionY += 20);
        }
    }
}
