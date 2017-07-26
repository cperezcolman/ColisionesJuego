package mundo;

import java.awt.*;

import static main.PanelJuego.ALTO;
import static main.PanelJuego.ANCHO;

public class FondoNegroConTexto extends Fondo{

    private long duracion;
    private boolean iniciada;
    private long tiempoInicio;
    private boolean termino;
    private String texto;
    private int tamano;

    public FondoNegroConTexto(long duracion, String texto, int tamano) {
        this.duracion = duracion;
        iniciada = false;
        termino = false;
        color = Color.BLACK;
        this.texto = texto;
        this.tamano = tamano;
    }

    public FondoNegroConTexto(String texto, int tamano) {
        this.duracion = 0;
        iniciada = false;
        termino = false;
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
            termino = true;
        }

    }

    @Override
    public void dibujar(Graphics2D g) {
        super.dibujar(g);
        Font font = new Font("Showcard Gothic", Font.PLAIN, tamano);
        g.setColor(Color.WHITE);
        dibujarTextoCentrado(g, texto, new Rectangle(ANCHO, ALTO), font);
    }

    public boolean yaTermino() {
        return termino;
    }

    public static void dibujarTextoCentrado(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public static void dibujarTextoCentradoHorizontalmente(Graphics g, String text, Font font, int ancho, int y) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = (ancho - metrics.stringWidth(text)) / 2;
        g.setFont(font);
        g.drawString(text, x, y);
    }

    public void reiniciar() {
        iniciada = false;
        termino = false;
    }

}
