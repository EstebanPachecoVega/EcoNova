package com.ecomarket.pedido.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ecomarket.pedido.model.PedidoItem;
import com.ecomarket.pedido.model.PedidoM;


//PDIT = PedidoItem
@Repository
public class ItemRepository {

    private List<PedidoItem> listaItems = new ArrayList<>();

    public List<PedidoItem> obtenerPDIT(){
        return listaItems;
    }

    public PedidoItem buscarPDIT_ID( Long id ){
        for ( PedidoItem aux : listaItems ){
            if ( aux.getProductId().equals(id) ){
                return aux;
            }
        }
        return null;
    }

    public PedidoItem actualizarPDIT( PedidoItem pdit , PedidoM pdm ){
        Long id= -1L;
        int posicion= 0;
        for ( int i = 0; i < listaItems.size(); i++ ){
            if ( listaItems.get(i).getProductId() == pdit.getProductId()){
                id = pdit.getProductId();
                posicion= i;
            }
        }

        PedidoItem pdit2 = new PedidoItem();

        pdit2.setIdp_Item(pdit.getIdp_Item());
        pdit2.setProductId(id);
        pdit2.setUnitPrice(pdit.getUnitPrice());
        pdit2.setPedido(pdm);
        listaItems.remove(posicion);
        listaItems.add(pdit2);

        return pdit2;
    }

    public PedidoItem guardarPDIT(PedidoItem pdit){
        listaItems.add(pdit);
        return pdit;
    }

    public void eliminarPDIT(Long id) {
        PedidoItem pdit = buscarPDIT_ID(id);
        if ( pdit != null){
            listaItems.remove(pdit);
        }
    }

}
