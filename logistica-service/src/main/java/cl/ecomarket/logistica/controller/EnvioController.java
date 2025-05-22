package cl.ecomarket.logistica.controller;

import cl.ecomarket.logistica.model.Envio;
import cl.ecomarket.logistica.service.EnvioService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/envio/envios")
public class EnvioController {

    @Autowired
    private EnvioService envioService;

    @GetMapping
    public ResponseEntity<List<Envio>> listaEnvios(){
        List<Envio> envios=envioService.getEnvios();
        if (envios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(envios);
    }

    @PostMapping
    public ResponseEntity<Envio> agregarEnvio(@RequestBody Envio env){
        Envio envio=envioService.saveEnvio(env);
        return ResponseEntity.status(HttpStatus.CREATED).body(envio);
    }

    @GetMapping("{id}")
    public ResponseEntity<Envio> buscarEnvio(@PathVariable long id){
        try {
            Envio env=envioService.getEnvioById(id);
            return ResponseEntity.ok(env);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Envio> actualizarEnvio(@PathVariable long id, @RequestBody Envio env){
        try {
            Envio envio = envioService.getEnvioById(id);
            envio.setIdPedido(env.getIdPedido());
            envio.setProveedor(env.getProveedor());
            envio.setIdProveedorEnvio(env.getIdProveedorEnvio());
            envio.setStatus(env.getStatus());
            envio.setFechaCreacion(env.getFechaCreacion()); //preguntar si esto esta bien
            envio.setFechaActualizacion(env.getFechaActualizacion());

            envioService.saveEnvio(envio);

            return ResponseEntity.ok(envio);
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("{id}")
    public ResponseEntity<?> eliminarEnvio(@PathVariable long id){
        try {
            envioService.deleteEnvio(id);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
