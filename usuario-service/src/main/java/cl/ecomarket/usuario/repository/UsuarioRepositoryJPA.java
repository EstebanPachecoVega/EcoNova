package cl.ecomarket.usuario.repository;

import cl.ecomarket.usuario.model.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositoryJPA extends JpaRepository<Usuario, Long>{

    Usuario findById(long id);
    String deleteById(long id);
}
