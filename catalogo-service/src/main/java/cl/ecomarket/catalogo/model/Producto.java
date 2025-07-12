package cl.ecomarket.producto.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "productos")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código es obligatorio")
    @Pattern(regexp = "^[A-Z0-9_-]{3,20}$", message = "El código debe tener entre 3 y 20 caracteres y solo puede contener letras mayúsculas, números, guiones o subrayados")
    @Column(nullable = false, unique = true)
    private String codigo;

    @NotBlank(message = "El código de barras es obligatorio")
    @Pattern(regexp = "^\\d{12,13}$", message = "El código de barras debe tener 12 o 13 dígitos")
    @Column(name = "codigo_barras", nullable = false, unique = true)
    private String codigoBarras;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false)
    private String nombre;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String descripcion;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @NotBlank(message = "La unidad de medida es obligatoria")
    @Column(name = "unidad_medida", nullable = false)
    private String unidadMedida;

    @Min(value = 0, message = "El precio debe ser positivo")
    private Integer precio;

    @Min(value = 0, message = "El stock disponible debe ser al menos 0")
    @Column(name = "stock_disponible", nullable = false)
    private Integer stockDisponible;

    @Column(nullable = false)
    private String estado; // DISPONIBLE o AGOTADO

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    @PrePersist
    public void prePersist() {
        this.fechaCreacion = LocalDateTime.now();
        this.fechaActualizacion = LocalDateTime.now();
        this.estado = (this.stockDisponible > 0) ? "DISPONIBLE" : "AGOTADO"; // Determina el estado
    }

    @PreUpdate
    public void preUpdate() {
        this.fechaActualizacion = LocalDateTime.now();
        this.estado = (this.stockDisponible > 0) ? "DISPONIBLE" : "AGOTADO"; // Actualiza el estado
    }
}

