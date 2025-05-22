package cl.ecomarket.logistica.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "envios")
@Data
public class Envio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //identificador principal del envio

    @Column(nullable = false)
    private long idPedido; //id de a que pedido pertenece este envio

    @Column(nullable = false)
    private String proveedor; //nombre del proveedor,por ejemplo chilexpress,starken

    private String idProveedorEnvio; //id del proveedor, por ejemplo chilexpress
    
    @Column(nullable = false)
    private String status; //en camino, entregado, devuelto,etc
    
    @Column(nullable = false)
    private LocalDateTime fechaCreacion; //fecha de "creacion del envio"
    private LocalDateTime fechaActualizacion; //fecha de actualizacion del envio (como cambio de estado)
}
