package com.ecomarket.pedido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ecomarket.pedido.model.PedidoItem;
import com.ecomarket.pedido.model.PedidoM;
import com.ecomarket.pedido.repository.ItemRepositoryJPA;
import com.ecomarket.pedido.repository.PedidoRepositoryJPA;

import jakarta.persistence.EntityNotFoundException;
@Service
public class ItemService {

    @Autowired
    private ItemRepositoryJPA IRPjpa;

    @Autowired
    @Qualifier("pedidoRepositoryJPA")
    private PedidoRepositoryJPA pdmJpa;

    public List<PedidoItem> getPdItems (){
        return IRPjpa.findAll();
    }

    public void actualizarTotalPedido(Long pedidoId) {
        int total = IRPjpa.calcularTotalPedido(pedidoId);
        PedidoM pedido = pdmJpa.findByUserId(pedidoId)
        .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
    
        pedido.setTotalAmount(total);
        pdmJpa.save(pedido);
    }
    public PedidoItem savePedidoItem ( PedidoItem pdit ){
        return IRPjpa.save(pdit);
    }

    public Optional<PedidoItem> getPedidoItemID ( Long id ){
        return IRPjpa.findByPedido_UserId(id);
    }

    public PedidoItem updatePedidoItem(Long id, PedidoItem pdm) {
        return IRPjpa.findById(id).map(pedidoExistente -> {
            pdm.setProductId(pedidoExistente.getProductId());
            IRPjpa.deleteById(id);
            return IRPjpa.save(pdm);
        }).orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));
    }
    

    public boolean existsById(Long id) {
        return IRPjpa.existsById(id);// Verifica si el pedido existe
    }

    public String deleteItem(Long id) {
        if (IRPjpa.existsById(id)) {
            IRPjpa.deleteById(id); 
            return "Producto eliminado.";
        } else {
            return "Producto no encontrado.";
        }
    }
}
