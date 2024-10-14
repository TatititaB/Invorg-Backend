package com.invorg.controlador;

import com.invorg.entidades.Remision;
import com.invorg.excepciones.ResourseNotFoundException;
import com.invorg.servicios.ServicioRemision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api-remision")
@CrossOrigin(origins = "http:/localhost:4200/")
public class ControladorRemision {

    private static final Logger logger = LoggerFactory.getLogger(ControladorRemision.class);

    @Autowired
    private ServicioRemision servicioRemision;

    @GetMapping("/remision")
    public ResponseEntity<List<Remision>> listaRemision(){
        List<Remision> listaRemision = servicioRemision.listaRemision();
        logger.info("Lista de remisiones obtenidas");
        listaRemision.forEach(remision -> logger.info(remision.toString()));
        return ResponseEntity.ok(listaRemision);
    }
    @GetMapping("/remision/{idRemision}")
    public ResponseEntity<Remision> buscarRemisionPorId(@PathVariable Long idRemision){
        Remision remisionEncontrada = servicioRemision.buscarRemisionPorId(idRemision);
        if (remisionEncontrada != null){
            return ResponseEntity.ok(remisionEncontrada);
        }else {
            throw new ResourseNotFoundException("No se encontro la remision con ID: " + idRemision);
        }
    }

    @PostMapping("/remision")
    @ResponseStatus(HttpStatus.CREATED)
    public Remision guardarRemision(@RequestBody Remision remision){
        logger.info("Remision agregado: "+ remision);
        return servicioRemision.guardarRemision(remision);
    }
    @PutMapping("/remision/{idRemision}")
    public ResponseEntity<Remision> actualizarRemision(
            @PathVariable Long idRemision,
            @RequestBody Remision remision){
        Remision remisionEncontrada = servicioRemision.buscarRemisionPorId(idRemision);
        if (remisionEncontrada != null){
            remisionEncontrada.setFecha_remision(remision.getFecha_remision());
            remisionEncontrada.setNumeroRemision(remision.getNumeroRemision());
            remisionEncontrada.setCantidadProductos(remision.getCantidadProductos());
            Remision remisionActualizada = servicioRemision.guardarRemision(remisionEncontrada);
            return ResponseEntity.ok(remisionActualizada);
        }else {
            throw new ResourseNotFoundException("Recurso no encontrado con ID: "+ idRemision);
        }
    }

    @DeleteMapping("/remision/{idRemision}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarRemision(@PathVariable Long idRemision){
        Remision remision = this.servicioRemision.buscarRemisionPorId(idRemision);
        if (remision == null){
            throw new ResourseNotFoundException("Id no encontrado: "+idRemision);
        }
        this.servicioRemision.eliminarRemision(idRemision);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Remision eliminada", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
