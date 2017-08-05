package mundo;

import util.Util;

import java.awt.*;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public class FondoNegroConTexto extends Fondo {

    private long duracion;
    private boolean iniciada;
    private long tiempoInicio;
    private boolean visible;
    private String texto;
    private int tamano;

    public FondoNegroConTexto(long duracion, String texto, int tamano) {
        this.duracion = duracion;
        iniciada = false;
        visible = true;
        color = Color.BLACK;
        this.texto = texto;
        this.tamano = tamano;
    }

    public void actualizar() {
        if (!iniciada) {
            tiempoInicio = System.currentTimeMillis();
            iniciada = true;
        }

        long transcurrido = (System.currentTimeMillis() - tiempoInicio);

        if (transcurrido >= duracion && duracion > 0) {
            visible = false;
        }

    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
        Font font = new Font("Showcard Gothic", Font.PLAIN, tamano);
        g.setColor(Color.WHITE);
        Util.dibujarTextoCentrado(g, texto, ANCHO, ALTO, font);
    }

    public boolean esVisible() {
        return visible;
    }

    public void reiniciar() {
        iniciada = false;
        visible = true;
    }

}
