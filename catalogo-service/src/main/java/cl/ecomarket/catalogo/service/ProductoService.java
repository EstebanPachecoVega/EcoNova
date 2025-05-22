package cl.ecomarket.catalogo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ecomarket.catalogo.model.Producto;
import cl.ecomarket.catalogo.repository.ProductoRepositoryJPA;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepositoryJPA productoRepository;

    public List<Producto> getProductos(){
        return productoRepository.findAll();
    }

    public Producto getProductoById(long id){
        return productoRepository.findById(id);
    }

    public Producto saveProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Producto producto){
        return productoRepository.save(producto);
    }

    public String deleteProducto(long id){
        productoRepository.deleteById(id);
        return "Producto Eliminado";
    }
}