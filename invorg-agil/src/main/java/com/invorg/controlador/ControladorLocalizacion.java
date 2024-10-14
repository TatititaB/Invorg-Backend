package com.invorg.controlador;

import com.invorg.entidades.Localizacion;
import com.invorg.excepciones.ResourseNotFoundException;
import com.invorg.servicios.ServicioLocalizacion;
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
@RequestMapping("api-localizacion")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorLocalizacion {

    private static final Logger logger = LoggerFactory.getLogger(ControladorLocalizacion.class);

    @Autowired
    private ServicioLocalizacion servicioLocalizacion;

    @GetMapping("/localizacion")
    public ResponseEntity<List<Localizacion>> listaLocalizacion(){
        List<Localizacion> listaLocalizacion = servicioLocalizacion.listaLocalizacion();
        logger.info("Lista de Localizaciones Obtenidas: ");
        listaLocalizacion.forEach(localizacion -> logger.info(localizacion.toString()));
        return ResponseEntity.ok(listaLocalizacion);
    }
    @GetMapping("/localizacion/{idLocalizacion}")
    public ResponseEntity<Localizacion> buscarLocalizacionPorId(@PathVariable Long idLocalizacion){
        Localizacion localizacionEncontrada = servicioLocalizacion.buscarLocalizacionPorId(idLocalizacion);
        if (localizacionEncontrada != null){
            return ResponseEntity.ok(localizacionEncontrada);
        }else{
            throw new ResourseNotFoundException("No se encontro la localizacion con ID: " + idLocalizacion);
        }
    }
    @PostMapping("/localizacion")
    @ResponseStatus(HttpStatus.CREATED)
    public Localizacion guardarLocalizacion(@RequestBody Localizacion localizacion){
        logger.info("Localizacion agregada: "+ localizacion);
        return servicioLocalizacion.guardarLocalizacion(localizacion);
    }
    @PutMapping("/localizacion/{idLocalizacion}")
    public ResponseEntity<Localizacion> actualizarLocalizacion(
            @PathVariable Long idLocalizacion,
            @RequestBody Localizacion localizacion){
        Localizacion localizacionEncontrada = servicioLocalizacion.buscarLocalizacionPorId(idLocalizacion);
        if(localizacionEncontrada != null){
            localizacionEncontrada.setCiudad(localizacion.getCiudad());
            localizacionEncontrada.setPais(localizacion.getPais());
            localizacionEncontrada.setDepartamento(localizacion.getDepartamento());
            Localizacion localizacionActualizada = servicioLocalizacion.guardarLocalizacion(localizacionEncontrada);
            return ResponseEntity.ok(localizacionActualizada);
        }else {
            throw new ResourseNotFoundException("Recurso no encontrado con ID: " + idLocalizacion);
        }
    }

    @DeleteMapping("/localizacion/{idLocalizacion}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarLocalizacion(@PathVariable Long idLocalizacion){
        Localizacion localizacion = this.servicioLocalizacion.buscarLocalizacionPorId(idLocalizacion);
        if (localizacion ==null) {
            throw new ResourseNotFoundException("Id no encontrada: " +idLocalizacion);
        }
        this.servicioLocalizacion.eliminarLocalizacion(idLocalizacion);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Localizacion eliminada", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
