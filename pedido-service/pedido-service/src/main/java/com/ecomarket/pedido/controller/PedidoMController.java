package com.ecomarket.pedido.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RestController;

import com.ecomarket.pedido.model.PedidoM;
import com.ecomarket.pedido.service.PedidoMService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
public class PedidoMController {

    @Autowired
    private PedidoMService pdmService;

    @GetMapping("/pedidos")
    public ResponseEntity<List<PedidoM>> listarPedido () {
        List<PedidoM> pedidoMs = pdmService.getPedidoM();
        if (pedidoMs.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidoMs);
    }

    @PostMapping("/pedido/sav")
    public ResponseEntity<PedidoM> agregarPedido(@RequestBody PedidoM pdm) {
        PedidoM nuevoPedido = pdmService.savePedidoM(pdm);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }


    @GetMapping("/pedido/get/{id}")
    public ResponseEntity<Optional<PedidoM>> buscarPedido_id( @PathVariable Long id ){
        
        try {
            Optional<PedidoM> pedido = pdmService.getPedidoMID(id);
                return ResponseEntity.ok( pedido);
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
        }
        
    }
    
    @PutMapping("/pedido/udp/{id}")
    public ResponseEntity<PedidoM> actualizarPedido(@PathVariable Long id, @RequestBody PedidoM pedido) {
        pedido.setIdp(id);
        PedidoM pedidoActualizado = pdmService.updatePedidoM(pedido);
        return ResponseEntity.ok(pedidoActualizado);
    }



    @DeleteMapping("/pedido/del/{id}")
    public ResponseEntity<?> eliminarPedido( @PathVariable Long id ) {
        try {
            pdmService.deletePedidoM(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
             return ResponseEntity.notFound().build();
        }
    }
 
}
