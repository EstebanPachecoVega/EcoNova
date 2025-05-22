package cl.ecomarket.inventario.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import cl.ecomarket.inventario.model.Inventario;

@Repository
public class InventarioRepository {

    private List<Inventario> listaInventarios=new ArrayList<>();

    public List<Inventario> obtenerInventarios(){
        return listaInventarios;
    }

    public Inventario buscarPorId(long id){
        for (Inventario inventario : listaInventarios) {
            if (inventario.getId()==id) {
                return inventario;
            }
        }
        return null;
    }

    public Inventario buscarPorIdProducto(long idProducto){
        for (Inventario inventario : listaInventarios) {
            if (inventario.getIdProducto()==idProducto) {
                return inventario;
            }
        }
        return null;
    }

    public Inventario guardar(Inventario inv){
        listaInventarios.add(inv);
        return inv;
    }

    public Inventario actualizar(Inventario inv){
        long id=0;
        int posicion=0;
        for (int i = 0; i < listaInventarios.size(); i++) {
            if (listaInventarios.get(i).getId()==inv.getId()) {
                id=inv.getId();
                posicion=i;
            }
        }
        Inventario inventario1=new Inventario();
        inventario1.setId(id);
        inventario1.setIdProducto(inv.getIdProducto());
        inventario1.setDisponible(inv.getDisponible());
        inventario1.setReservado(inv.getReservado());

        listaInventarios.set(posicion, inventario1);
        return inventario1;
    }

    public void eliminar(long id){
        Inventario inv=buscarPorId(id);
        if (inv!=null) {
            listaInventarios.remove(inv);
        }
    }
}