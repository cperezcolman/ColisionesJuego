package estado;

class EstadoJugador {

    private static int cantidadVidas;

    static void iniciar() {
        cantidadVidas = 5;
    }

    static int getCantidadVidas() {
        return cantidadVidas;
    }

    static void setCantidadVidas(int cantidadVidas) {
        EstadoJugador.cantidadVidas = cantidadVidas;
    }
}
