package cl.ecomarket.catalogo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "productos")
@Data
@NoArgsConstructor

public class Producto {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long id;

@Column(nullable = false)
private String nombre;

@Column(nullable = false)
private String descripcion;

@Column(nullable = false)
private int precio;

@Column(nullable = false)
private int stock;

@Column(nullable = false)
private String categoria;
private Boolean ecoFriendly;
}