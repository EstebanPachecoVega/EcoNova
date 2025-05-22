package com.ecomarket.pedido.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.ecomarket.pedido.model.StatusComplement.StatusPedido;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;


@Entity
@Table (name = "pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor
//PedidoModel
public class PedidoM {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idp;

    @Column(nullable = false)
    private Long userId;

    @Column
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime fecha = LocalDateTime.now();

    @Column(nullable = false)
    private int totalAmount;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    @JsonManagedReference
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PedidoItem> items = new ArrayList<>();
}

