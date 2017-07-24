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

    public FondoNegroConTexto(long duracion, String texto) {
        this.duracion = duracion;
        iniciada = false;
        termino = false;
        color = Color.BLACK;
        this.texto = texto;
    }

    public FondoNegroConTexto(String texto) {
        this.duracion = 0;
        iniciada = false;
        termino = false;
        color = Color.BLACK;
        this.texto = texto;
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
        Font font = new Font("Showcard Gothic", Font.PLAIN, 50);
        drawCenteredString(g, texto, new Rectangle(ANCHO, ALTO), font);
    }

    public boolean yaTermino() {
        return termino;
    }

    private void drawCenteredString(Graphics g, String text, Rectangle rect, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = rect.x + (rect.width - metrics.stringWidth(text)) / 2;
        int y = rect.y + ((rect.height - metrics.getHeight()) / 2) + metrics.getAscent();
        g.setFont(font);
        g.setColor(Color.WHITE);
        g.drawString(text, x, y);
    }

}
