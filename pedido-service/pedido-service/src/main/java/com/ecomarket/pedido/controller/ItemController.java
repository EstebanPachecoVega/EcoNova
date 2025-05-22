package com.ecomarket.pedido.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecomarket.pedido.model.PedidoItem;
import com.ecomarket.pedido.service.ItemService;

@RestController
public class ItemController {
    @Autowired
    private ItemService iServ;

    @GetMapping("/items")
    public ResponseEntity<List<PedidoItem>> listarItems() {
        List<PedidoItem> pedidoItem = iServ.getPdItems();
        if (pedidoItem.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pedidoItem);
    }

    @PostMapping("/item/sav")
    public ResponseEntity<PedidoItem> agregarItem(@RequestBody PedidoItem pdit) {
        PedidoItem nuevoItem = iServ.savePedidoItem(pdit);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoItem);
    }


    @GetMapping("/item/get/{id}")
    public ResponseEntity<Optional<PedidoItem>> buscarItem_id( @PathVariable Long id ){
        try {
            Optional<PedidoItem> item = iServ.getPedidoItemID(id);
                return ResponseEntity.ok( item );
            } catch (Exception e) {
                return ResponseEntity.notFound().build();
        }   
    }
    
    @PutMapping("/item/udp/{id}")
    public ResponseEntity<PedidoItem> actualizarPedido(@PathVariable Long id, @RequestBody PedidoItem pdit ) {
        try {
            PedidoItem nuevoItem = iServ.updatePedidoItem(id, pdit);
            return ResponseEntity.ok(nuevoItem);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/item/del/{id}")
    public ResponseEntity<?> eliminarPedido( @PathVariable Long id ) {
        try {
            iServ.deleteItem(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
             return ResponseEntity.notFound().build();
        }
    }
}
