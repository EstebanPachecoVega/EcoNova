package com.ecomarket.pedido.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ecomarket.pedido.model.PedidoItem;

@Repository
public interface ItemRepositoryJPA extends JpaRepository< PedidoItem, Long>{

    Optional<PedidoItem> findByPedido_UserId(Long userId);

    //Para totalAmount
    @Query("SELECT SUM(p.unitPrice) FROM PedidoItem p WHERE p.pedido.id = :pedidoId")
    Integer calcularTotalPedido(@Param("pedidoId") Long pedidoId);
}