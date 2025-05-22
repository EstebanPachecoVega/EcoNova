package cl.ecomarket.compra.cliente;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import lombok.Data;

@Component
public class CatalogoCliente {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public CatalogoCliente(RestTemplate restTemplate,
                         @Value("${catalogo.service.url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public Integer obtenerPrecio(Long idProducto) {
        // Llamada al Catálogo
        Producto prod = restTemplate.getForObject(
            baseUrl + "/productos/" + idProducto,
            Producto.class
        );

        // Verificar que no sea null
        if (prod == null) {
            throw new RuntimeException(
            "No se encontró el producto con ID " + idProducto);
        }

        return prod.getPrecio();
    }

    @Data // getters y setters
    public static class Producto {
        private Long id;
        private String nombre;
        private Integer precio;
    }
}
