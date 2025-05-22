package cl.ecomarket.compra.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

import cl.ecomarket.compra.enums.EstadoPedido;

@Entity
@Table(name = "pedidos")
@Data
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long usuarioId;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private Integer total;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoPedido estado; // PENDIENTE, CONFIRMADO, CANCELADO

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> items;
}
