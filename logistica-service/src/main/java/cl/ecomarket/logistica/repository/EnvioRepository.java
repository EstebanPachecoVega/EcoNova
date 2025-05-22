package cl.ecomarket.logistica.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import cl.ecomarket.logistica.model.Envio; 

@Repository
public class EnvioRepository {

    private List<Envio> listaEnvios= new ArrayList<>();

    public List<Envio> obtenerEnvios(){
        return listaEnvios;
    }

    public Envio buscarPorId(long id){
        for (Envio envio : listaEnvios) {
            if (envio.getId()==id) {
                return envio;
            }
        }
        return null;
    }

    public Envio buscarPoridProveedorEnvio(String idProveedorEnvio){
        for (Envio envio : listaEnvios) {
            if (envio.getIdProveedorEnvio().equals(idProveedorEnvio)) {
                return envio;
            }
        }
        return null;
    }

    public Envio guardar(Envio env){
        listaEnvios.add(env);
        return env;
    }

    public Envio actualizar(Envio env){
        long id=0;
        int posicion=0;
        for (int i = 0; i < listaEnvios.size(); i++) {
            if (listaEnvios.get(i).getId()==env.getId()) {
                id=env.getId();
                posicion=i;
            }
        }
        Envio envio1=new Envio();
        envio1.setId(id);
        envio1.setIdPedido(env.getIdPedido());
        envio1.setProveedor(env.getProveedor());
        envio1.setIdProveedorEnvio(env.getIdProveedorEnvio());
        envio1.setStatus(env.getStatus());
        envio1.setFechaCreacion(env.getFechaCreacion()); //consultar si esto esta bien
        envio1.setFechaActualizacion(env.getFechaActualizacion());

        listaEnvios.set(posicion, envio1);
        return envio1;
    }   

    public void eliminar(long id){
        Envio env=buscarPorId(id);
        if (env!=null) {
            listaEnvios.remove(env);
        }
    }    
}