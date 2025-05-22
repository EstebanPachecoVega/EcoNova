package cl.ecomarket.compra.excepcion;

public class PedidoNotFoundException extends RuntimeException {
    public PedidoNotFoundException(String mensaje) {
        super(mensaje);
    }
}
