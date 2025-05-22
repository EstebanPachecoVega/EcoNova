package cl.ecomarket.compra.controller;

import cl.ecomarket.compra.model.Pedido;
import cl.ecomarket.compra.service.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compra")
public class CompraController {

    @Autowired
    private CompraService servicio;

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(servicio.procesarCompra(pedido));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(servicio.obtenerPedido(id));
    }

}
