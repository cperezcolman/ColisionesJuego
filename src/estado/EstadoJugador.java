package estado;

public class EstadoJugador {

    private static int cantidadVidas;

    static void iniciar() {
        cantidadVidas = 5;
    }

    public static int getCantidadVidas() {
        return cantidadVidas;
    }

    public static void setCantidadVidas(int cantidadVidas) {
        EstadoJugador.cantidadVidas = cantidadVidas;
    }
}
