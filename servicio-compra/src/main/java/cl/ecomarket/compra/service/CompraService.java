package cl.ecomarket.compra.service;

import cl.ecomarket.compra.cliente.CatalogoCliente;
import cl.ecomarket.compra.cliente.InventarioCliente;
import cl.ecomarket.compra.enums.EstadoPedido;
import cl.ecomarket.compra.excepcion.PedidoNotFoundException;
import cl.ecomarket.compra.model.Pedido;
import cl.ecomarket.compra.repository.PedidoRepositoryJPA;
import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompraService {

    private static final Logger log = LoggerFactory.getLogger(CompraService.class);

    private final PedidoRepositoryJPA repo;
    private final CatalogoCliente catalogoCliente;
    private final InventarioCliente inventarioCliente;

    public CompraService(PedidoRepositoryJPA repo,
                         CatalogoCliente catalogoCliente,
                         InventarioCliente inventarioCliente) {
        this.repo = repo;
        this.catalogoCliente = catalogoCliente;
        this.inventarioCliente = inventarioCliente;
    }

    @Transactional
    public Pedido procesarCompra(Pedido pedido) {
        int total = 0;
        // Calcular precios y reservar stock
        for (var item : pedido.getItems()) {
            inventarioCliente.verificarYReservar(item.getProductoId(), item.getCantidad());
            int precio = catalogoCliente.obtenerPrecio(item.getProductoId());
            item.setPrecioUnitario(precio);
            total += precio * item.getCantidad();
        }

        // Inicializar campos de pedido
        pedido.setFechaCreacion(LocalDateTime.now());
        pedido.setTotal(total);
        pedido.setEstado(EstadoPedido.PENDIENTE);
        pedido.getItems().forEach(i -> i.setPedido(pedido));

        log.info("Procesando compra: usuario={}, total={}, items={}",
                 pedido.getUsuarioId(), total, pedido.getItems().size());

        // Guardar pedido pendiente
        Pedido guardado = repo.save(pedido);
        log.info("Pedido {} guardado con estado {}", guardado.getId(), guardado.getEstado());

        // Confirmar pedido
        guardado.setEstado(EstadoPedido.CONFIRMADO);
        log.info("Pedido {} confirmado", guardado.getId());

        return repo.save(guardado);
    }

    
    public Pedido obtenerPedido(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new PedidoNotFoundException(
                "Pedido no encontrado con id " + id
            ));
    }

}
