package com.ecomarket.pedido.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonBackReference;


import jakarta.persistence.*;


@Entity
@Table (name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idp_Item;

    @Column(nullable = false)
    private Long productId;

    @Column(nullable = false)
    private int unitPrice;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoM pedido;
}
