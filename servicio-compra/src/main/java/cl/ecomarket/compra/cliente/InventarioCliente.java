package cl.ecomarket.compra.cliente;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Component
public class InventarioCliente {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public InventarioCliente(RestTemplate restTemplate,
                           @Value("${inventario.service.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public void verificarYReservar(Long idProducto, Integer cantidad) {
        // 1) Obtener stock
        Stock stock = restTemplate.getForObject(baseUrl + "/" + idProducto, Stock.class);
        if (stock == null) {
            throw new RuntimeException("No se pudo obtener el stock para el producto " + idProducto);
        }
        if (stock.getDisponible() < cantidad) {
            throw new RuntimeException("Stock insuficiente para producto " + idProducto);
        }

        // 2) Reservar
        ReservaRequest req = new ReservaRequest(idProducto, cantidad);
        restTemplate.postForObject(baseUrl + "/reservar", req, Void.class);
    }

    @Data // getters y setters
    public static class Stock {
        private Long idProducto;
        private Integer disponible;
        private Integer reservado;
    }

    @Getter // getters
    @AllArgsConstructor
    public static class ReservaRequest {
        private Long idProducto;
        private Integer cantidad;
    }
}
