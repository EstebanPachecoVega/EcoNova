package cl.ecomarket.usuario.controller;

import cl.ecomarket.usuario.model.Usuario;
import cl.ecomarket.usuario.service.UsuarioService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listaUsuarios(){
        List<Usuario> usuarios = usuarioService.getUsuarios();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> agregarUsuario(@RequestBody Usuario usu){
        Usuario usuario=usuarioService.saveUsuario(usu);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
    
    @GetMapping("{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable long id){
        try {
            Usuario usu=usuarioService.getUsuarioById(id);
            return ResponseEntity.ok(usu);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable long id, @RequestBody Usuario usu){
        try {
            Usuario usuario = usuarioService.getUsuarioById(id);
            usuario.setPnombre(usu.getPnombre());
            usuario.setApellido(usu.getApellido());
            usuario.setCorreo(usu.getCorreo());
            usuario.setRol(usu.getRol());
            usuario.setActiva(usu.getActiva());

            usuarioService.saveUsuario(usuario);

            return ResponseEntity.ok(usuario);
        
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarLibro(@PathVariable long id){
        try {
            usuarioService.deleteLibro(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }   
    }

}