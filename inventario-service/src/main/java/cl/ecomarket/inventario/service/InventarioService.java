package cl.ecomarket.inventario.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.ecomarket.inventario.model.Inventario;
import cl.ecomarket.inventario.repository.InventarioRepositoryJPA;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class InventarioService {

    @Autowired
    private InventarioRepositoryJPA inventarioRepository;

    public List<Inventario> getInventarios(){
        return inventarioRepository.findAll();
    }

    public Inventario getInventarioById(long id){
        return inventarioRepository.findById(id);
    }

    public Inventario saveInventario(Inventario inventario){
        return inventarioRepository.save(inventario);
    }

    public Inventario updateInventario(Inventario inventario){
        return inventarioRepository.save(inventario);
    }

    public String deleteInventario(long id){
        inventarioRepository.deleteById(id);
        return "Inventario Eliminado";
    }
}
