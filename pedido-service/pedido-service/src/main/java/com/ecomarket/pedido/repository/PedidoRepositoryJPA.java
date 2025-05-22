package com.ecomarket.pedido.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecomarket.pedido.model.PedidoItem;
import com.ecomarket.pedido.model.PedidoM;



@Repository
public interface PedidoRepositoryJPA extends JpaRepository< PedidoM, Long>{
    //Item
    @Query("SELECT p FROM PedidoItem p WHERE p.pedido.id = :pedidoId")
    List<PedidoItem> findByPedidoId(@Param("pedidoId") Long pedidoId);
    //pedido
    Optional<PedidoM> findByUserId(Long userId);

}