package cl.ecomarket.logistica.repository;

import cl.ecomarket.logistica.model.Envio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvioRepositoryJPA extends JpaRepository <Envio, Long> {

    Envio findById(long id);
    String deleteById(long id);
}
