package com.ecomarket.pedido.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecomarket.pedido.model.PedidoM;
import com.ecomarket.pedido.repository.PedidoRepositoryJPA;

import jakarta.transaction.Transactional;



@Service
public class PedidoMService {
    @Autowired

    private PedidoRepositoryJPA pedidoRPJ;



    public List<PedidoM> getPedidoM (){
        return pedidoRPJ.findAll();
    }

    public PedidoM savePedidoM ( PedidoM pdm ){
        return pedidoRPJ.save(pdm);
    }

    public Optional<PedidoM> getPedidoMID ( Long id ){
        return pedidoRPJ.findByUserId(id);
    }

    @Transactional
    public PedidoM updatePedidoM(PedidoM pdm) {
        PedidoM pedidoExistente = pedidoRPJ.findById(pdm.getIdp())
            .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));

        pedidoExistente.setTotalAmount(pdm.getTotalAmount());
        pedidoExistente.setStatus(pdm.getStatus());

        return pedidoRPJ.save(pedidoExistente);

    }


    public boolean existsById(Long id) {
        return pedidoRPJ.existsById(id);// Verifica si el pedido existe
    }

    public String deletePedidoM(Long id) {
        if (pedidoRPJ.existsById(id)) {
            pedidoRPJ.deleteById(id); 
            return "Pedido eliminado.";
        } else {
            return "Pedido no encontrado.";
        }
    }

}
