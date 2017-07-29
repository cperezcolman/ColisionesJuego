package estado;

import estado.nivel.Nivel;
import estado.nivel.Nivel1;
import mundo.FondoNegroConTexto;

import java.awt.*;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public class PantallaInicio extends EstadoJuego {

    private ListaOpciones listaOpciones;

    public PantallaInicio() {
        super();
        Font font = new Font("Showcard Gothic", Font.PLAIN, 20);
        String[] opciones = {"Jugar", "Tutorial", "Salir"};
        listaOpciones = new ListaOpciones(opciones, font, 300, 40);
    }

    @Override
    public void actualizar() {
        listaOpciones.actualizar();

        if (listaOpciones.isOpcionSeleccionada()) {
            seleccionar();
        }

    }

    private void seleccionar() {

        int opcionSeleccionada = listaOpciones.getSeleccionActual();

        if (opcionSeleccionada == 0) {
            Nivel nivel1 = new Nivel1();
            manejadorJuegoJuego.establecerEstado(nivel1);
        } else if (opcionSeleccionada == 2) {
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
        FondoNegroConTexto.dibujarTextoCentrado(g, "C o l i s i o n e s", ANCHO, ALTO, font);
        dibujarOpciones(g);
    }

    private void dibujarOpciones(Graphics2D g) {
        listaOpciones.dibujar(g);
    }
}
