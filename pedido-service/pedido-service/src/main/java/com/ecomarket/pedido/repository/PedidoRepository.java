package com.ecomarket.pedido.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecomarket.pedido.model.PedidoItem;
import com.ecomarket.pedido.model.PedidoM;
@Repository
public class PedidoRepository {
    //PDM = PedidoM (modelo)
    private List<PedidoM> listaPedidos = new ArrayList<>();
    

    public List<PedidoM> obtenerPDM(){
        return listaPedidos;
    }

    public PedidoM buscarPDM_ID( Long id ){
        for ( PedidoM aux : listaPedidos ){
            if ( aux.getUserId().equals(id) ){
                return aux;
            }
        }
        return null;
    }

    public PedidoM actualizarPDM( PedidoM pdm, List<PedidoItem> nuevosItems ){
        Long id= null;
        int posicion= 0;
        for ( int i = 0; i < listaPedidos.size(); i++ ){
            if ( listaPedidos.get(i).getUserId() == pdm.getUserId()){
                id = pdm.getUserId();
                posicion= i;
            }
        }

        PedidoM pdm2 = new PedidoM();

        pdm2.setIdp(pdm.getIdp());
        pdm2.setUserId(id);
        pdm2.setFecha(LocalDateTime.now());
        pdm2.setTotalAmount(pdm.getTotalAmount());
        pdm2.setStatus(pdm.getStatus());
        pdm2.setItems(nuevosItems);
        listaPedidos.remove(posicion);
        listaPedidos.add(pdm2);

        return pdm2;
    }

    public PedidoM guardarPM(PedidoM pdm){
        listaPedidos.add(pdm);
        return pdm;
    }

    public void eliminarPDM(Long id) {
        PedidoM pdm = buscarPDM_ID(id);
        if ( pdm != null){
            listaPedidos.remove(pdm);
        }
    }
    
}
