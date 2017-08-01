package util;

import java.awt.*;

public class Util {

    public static void dibujarTextoCentrado(Graphics g, String text, int ancho, int alto, Font font) {
        FontMetrics metrics = g.getFontMetrics(font);
        int y = ((alto - metrics.getHeight()) / 2) + metrics.getAscent();
        dibujarTextoCentradoHorizontalmente(g, text, font, ancho, y);
    }

    public static void dibujarTextoCentradoHorizontalmente(Graphics g, String text, Font font, int ancho, int y) {
        FontMetrics metrics = g.getFontMetrics(font);
        int x = (ancho - metrics.stringWidth(text)) / 2;
        g.setFont(font);
        g.drawString(text, x, y);
    }

}
