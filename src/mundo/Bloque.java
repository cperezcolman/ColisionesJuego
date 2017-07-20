package mundo;

/**
 * @author Carlos Perez
 */
public class Bloque {

    public static final int TAMANO = 32;

    private int x;
    private int y;
    private int numeroBloque;
    private boolean bloqueado;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void bloquear() {
        this.bloqueado = true;
    }

    public boolean isBloqueado() {
        return bloqueado;
    }

    public int getNumeroBloque() {
        return numeroBloque;
    }

    public void setNumeroBloque(int numeroBloque) {
        this.numeroBloque = numeroBloque;
    }

}
