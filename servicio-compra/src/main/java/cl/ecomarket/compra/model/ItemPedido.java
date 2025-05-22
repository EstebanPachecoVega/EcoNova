package cl.ecomarket.compra.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "compras")
@Data
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long productoId;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Integer precioUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

}
