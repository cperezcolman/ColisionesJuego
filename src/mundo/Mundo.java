package mundo;

import main.PanelJuego;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Mundo {

    private static final double RETRASO = 0.07;

    private int x;
    private int y;

    private int xMin;
    private int xMax;
    private int yMin;
    private int yMax;

    private int cantidadTotalFilas;
    private int cantidadTotalColumnas;
    private int cantidadFilasADibujar;
    private int cantidadColumnasADibujar;
    private int columnaDesplazada;
    private int filaDesplazada;

    private Bloque[][] bloques;

    public Mundo(String ubicacionMapa) {

        cantidadFilasADibujar = PanelJuego.ALTO / Bloque.TAMANO + 1;
        cantidadColumnasADibujar = PanelJuego.ANCHO / Bloque.TAMANO + 1;

        cargarBloques(ubicacionMapa);

    }

    private void cargarBloques(String ubicacionMapa) {

        InputStream in = getClass().getClassLoader().getResourceAsStream(ubicacionMapa);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        try {

            cantidadTotalColumnas = Integer.parseInt(br.readLine());
            cantidadTotalFilas = Integer.parseInt(br.readLine());

            int anchoMapa = cantidadTotalColumnas * Bloque.TAMANO;
            int altoMapa = cantidadTotalFilas * Bloque.TAMANO;

            xMin = PanelJuego.ANCHO - anchoMapa;
            xMax = 0;
            yMin = PanelJuego.ALTO - altoMapa;
            yMax = 0;

            bloques = new Bloque[cantidadTotalFilas][cantidadTotalColumnas];

            String[] linea;
            int xBloque, yBloque;
            int numeroBloque;

            for (int i = 0; i < cantidadTotalFilas; i++) {

                linea = br.readLine().split(",");
                yBloque = i * Bloque.TAMANO;

                for (int j = 0; j < cantidadTotalColumnas; j++) {

                    xBloque = j * Bloque.TAMANO;
                    numeroBloque = Integer.parseInt(linea[j]);

                    bloques[i][j] = new Bloque();
                    bloques[i][j].setNumeroBloque(numeroBloque);

                    if (numeroBloque > 0) {
                        bloques[i][j].bloquear();
                    }

                    bloques[i][j].setX(xBloque);
                    bloques[i][j].setY(yBloque);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void establecerPosicion(int x, int y) {

        this.x += (x - this.x) * RETRASO;
        this.y += (y - this.y) * RETRASO;

//        this.x = x;
//        this.y = y;

        fijarLimites();

        filaDesplazada = -this.x / Bloque.TAMANO;
        columnaDesplazada = -this.y / Bloque.TAMANO;

    }

    private void fijarLimites() {
        if (x < xMin) {
            x = xMin;
        }
        if (y < yMin) {
            y = yMin;
        }
        if (x > xMax) {
            x = xMax;
        }
        if (y > yMax) {
            y = yMax;
        }
    }

    public void dibujar(Graphics2D g) {

        int numeroBloque;

        for (int fila = columnaDesplazada; fila < columnaDesplazada + cantidadFilasADibujar; fila++) {

            if (fila >= cantidadTotalFilas) {
                break;
            }

            for (int columna = filaDesplazada; columna < filaDesplazada + cantidadColumnasADibujar; columna++) {

                if (columna >= cantidadTotalColumnas) {
                    break;
                }

                numeroBloque = bloques[fila][columna].getNumeroBloque();

                if (numeroBloque == 0) {
                    continue;
                }

                g.setColor(Color.BLACK);
                g.drawRect(x + columna * Bloque.TAMANO, y + fila * Bloque.TAMANO, Bloque.TAMANO, Bloque.TAMANO);
            }
        }

    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Bloque[][] getBloques() {
        return bloques;
    }
}
