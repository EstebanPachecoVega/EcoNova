package cl.ecomarket.inventario.controller;

import cl.ecomarket.inventario.model.Inventario;
import cl.ecomarket.inventario.service.InventarioService;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventario/inventarios")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    @GetMapping
    public ResponseEntity<List<Inventario>> listaInventarios(){
        List<Inventario> inventarios=inventarioService.getInventarios();
        if (inventarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(inventarios);
    }

    @PostMapping
    public ResponseEntity<Inventario> agregarInventario(@RequestBody Inventario inv){
        Inventario inventario=inventarioService.saveInventario(inv);
        return ResponseEntity.status(HttpStatus.CREATED).body(inventario);
    }

    @GetMapping("{id}")
    public ResponseEntity<Inventario> buscarInventario(@PathVariable long id){
        try {
            Inventario inv=inventarioService.getInventarioById(id);
            return ResponseEntity.ok(inv);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Inventario> actualizarInventario(@PathVariable long id, @RequestBody Inventario inv){
        try {
            Inventario inventario=inventarioService.getInventarioById(id);
            //idproducto,disponible,reservado
            inventario.setIdProducto(inv.getIdProducto());
            inventario.setDisponible(inv.getDisponible());
            inventario.setReservado(inv.getReservado());

            inventarioService.saveInventario(inventario);

            return ResponseEntity.ok(inventario);      
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarInventario(@PathVariable long id){
        try {
            inventarioService.deleteInventario(id);
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
