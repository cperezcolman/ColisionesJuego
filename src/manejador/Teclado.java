package manejador;

import java.awt.event.KeyEvent;

public class Teclado {

    private static final int CANTIDAD_TECLAS = 6;

    public static boolean estado[] = new boolean[CANTIDAD_TECLAS];
    private static boolean estadoPrevio[] = new boolean[CANTIDAD_TECLAS];

    public static int ARRIBA = 0;
    public static int ABAJO = 1;
    public static int IZQUIERDA = 2;
    public static int DERECHA = 3;
    public static int ENTER = 4;
    public static int ESCAPE = 5;

    public static void establecerEstado(int i, boolean b) {
        switch (i) {
            case KeyEvent.VK_UP:
                estado[ARRIBA] = b;
                break;
            case KeyEvent.VK_DOWN:
                estado[ABAJO] = b;
                break;
            case KeyEvent.VK_LEFT:
                estado[IZQUIERDA] = b;
                break;
            case KeyEvent.VK_RIGHT:
                estado[DERECHA] = b;
                break;
            case KeyEvent.VK_ENTER:
                estado[ENTER] = b;
                break;
            case KeyEvent.VK_ESCAPE:
                estado[ESCAPE] = b;
                break;
        }
    }

    public static void actualizar() {
        for (int i = 0; i < CANTIDAD_TECLAS; i++) {
            estadoPrevio[i] = estado[i];
        }
    }

    public static boolean esPresionado(int i) {
        return estado[i] && !estadoPrevio[i];
    }

    public static boolean sePresionaCualquierTecla() {
        for (int i = 0; i < CANTIDAD_TECLAS; i++) {
            if (estado[i]) {
                return true;
            }
        }
        return false;
    }

}
