package cl.ecomarket.usuario.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.ecomarket.usuario.model.Usuario;
import cl.ecomarket.usuario.repository.UsuarioRepositoryJPA;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {

    @Autowired
    private UsuarioRepositoryJPA usuarioRepository;

    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }

    public Usuario getUsuarioById(long id){
        return usuarioRepository.findById(id);
    }

    public Usuario saveUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }
    
    public Usuario updateUsuario(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public String deleteLibro(long id){
        usuarioRepository.deleteById(id);
        return "Libro eliminado";
    }
}
