package cl.ecomarket.inventario.repository;

import cl.ecomarket.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventarioRepositoryJPA extends JpaRepository <Inventario, Long>{

    Inventario findById(long id);
    String deleteById(long id);
}
