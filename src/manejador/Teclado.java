package manejador;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Teclado {

    private static HashMap<Integer, Boolean> estado = new HashMap<>();
    private static HashMap<Integer, Boolean> estadoPrevio = new HashMap<>();

    private static List<Integer> teclas = new ArrayList<>();

    static {

        teclas.add(KeyEvent.VK_UP);
        teclas.add(KeyEvent.VK_DOWN);
        teclas.add(KeyEvent.VK_RIGHT);
        teclas.add(KeyEvent.VK_LEFT);
        teclas.add(KeyEvent.VK_ENTER);
        teclas.add(KeyEvent.VK_ESCAPE);

        for (Integer tecla : teclas) {
            estado.put(tecla, false);
        }

        estadoPrevio.putAll(estado);
    }

    public static void establecerEstado(int i, boolean b) {
        if (estado.containsKey(i)) {
            estado.put(i, b);
        }
    }

    public static void actualizar() {
        estadoPrevio.putAll(estado);
    }

    public static boolean esPresionado(int i) {
        return estado.get(i) && !estadoPrevio.get(i);
    }

    public static void forzarKeyReleased() {
        for (Integer tecla : teclas) {
            estado.put(tecla, false);
        }
    }

    public static boolean sePresionaCualquierTecla() {
        for (Integer tecla : teclas) {
            if (estado.get(tecla)) {
                return true;
            }
        }
        return false;
    }

    public static boolean getEstado(int i) {
        return estado.get(i);
    }

}
