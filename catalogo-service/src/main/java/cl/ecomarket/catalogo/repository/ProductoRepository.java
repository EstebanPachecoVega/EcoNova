package cl.ecomarket.catalogo.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cl.ecomarket.catalogo.model.Producto;

@Repository
public class ProductoRepository {

    private List<Producto> listaProductos=new ArrayList<>();

    public List<Producto>obtenerProductos(){
        return listaProductos;
    }

    public Producto buscarPorId(long id){
        for (Producto producto : listaProductos) {
            if (producto.getId()==id) {
                return producto;
            }
        }
        return null;
    }

    public Producto guardar(Producto pro){
        listaProductos.add(pro);
        return pro;
    }

    public Producto actualizar(Producto pro){
        long id=0;
        int posicion=0;
        for (int i = 0; i < listaProductos.size(); i++) {
            if (listaProductos.get(i).getId()==pro.getId()) {
                id=pro.getId();
                posicion=i;
            }
        }
        Producto producto1=new Producto();
        producto1.setId(id);
        producto1.setNombre(pro.getNombre());
        producto1.setDescripcion(pro.getDescripcion());
        producto1.setPrecio(pro.getPrecio());
        producto1.setStock(pro.getStock());
        producto1.setCategoria(pro.getCategoria());
        producto1.setEcoFriendly(pro.getEcoFriendly()); //boolean
        
        listaProductos.set(posicion, producto1);
        return producto1;
    }

    public void eliminar(long id){
        Producto pro=buscarPorId(id);
        if (pro!=null) {
            listaProductos.remove(pro);
        }
    }

    
}
