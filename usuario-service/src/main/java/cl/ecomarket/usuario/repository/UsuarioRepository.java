package cl.ecomarket.usuario.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
/*Importar usuario del package modelo*/
import cl.ecomarket.usuario.model.Usuario;

@Repository
public class UsuarioRepository {
private List<Usuario> listaUsuarios=new ArrayList<>();

public List<Usuario>obtenerUsuarios(){
    return listaUsuarios;
}

public Usuario buscarPorId(long id){
    for (Usuario usuario : listaUsuarios) {
        if (usuario.getId()==id) {
            return usuario;
        }
    }
    return null;
}

public Usuario guardar(Usuario usu){
    listaUsuarios.add(usu);
    return usu;
}

public Usuario actualizar(Usuario usu){
    long id=0;
    int posicion=0;
    for (int i = 0; i < listaUsuarios.size(); i++) {
        if (listaUsuarios.get(i).getId()==usu.getId()) {
            id=usu.getId();
            posicion=i;
        }
    }
    Usuario usuario1= new Usuario();
    usuario1.setId(id);
    usuario1.setPnombre(usu.getPnombre());
    usuario1.setApellido(usu.getApellido());
    usuario1.setCorreo(usu.getCorreo());
    usuario1.setRol(usu.getRol());
    usuario1.setActiva(usu.getActiva()); //boolean

    listaUsuarios.set(posicion, usuario1);
    return usuario1;
}

public void eliminar(long id){
    Usuario usu=buscarPorId(id);
    if (usu!=null) {
        listaUsuarios.remove(usu);
    }
}

}