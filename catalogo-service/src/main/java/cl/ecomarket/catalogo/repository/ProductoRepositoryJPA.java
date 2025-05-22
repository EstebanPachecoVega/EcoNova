package cl.ecomarket.catalogo.repository;

import cl.ecomarket.catalogo.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepositoryJPA extends JpaRepository<Producto, Long>{

    Producto findById(long id);
    String deleteById(long id);
}
