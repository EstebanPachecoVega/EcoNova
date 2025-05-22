package cl.ecomarket.logistica.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cl.ecomarket.logistica.model.Envio;
import cl.ecomarket.logistica.repository.EnvioRepositoryJPA;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class EnvioService {

    @Autowired
    private EnvioRepositoryJPA envioRepository;

    public List<Envio> getEnvios(){
        return envioRepository.findAll();
    }

    public Envio getEnvioById(long id){
        return envioRepository.findById(id);
    }

    public Envio saveEnvio(Envio envio){
        return envioRepository.save(envio);
    }

    public Envio updateEnvio(Envio envio){
        return envioRepository.save(envio);
    }

    public String deleteEnvio(long id){
        envioRepository.deleteById(id);
        return "Envio Eliminado";
    }
}