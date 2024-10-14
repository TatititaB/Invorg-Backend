package com.invorg.controlador;

import com.invorg.entidades.Persona;
import com.invorg.excepciones.ResourseNotFoundException;
import com.invorg.servicios.ServicioPersona;
import org.hibernate.internal.CoordinatingEntityNameResolver;
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
@RequestMapping("api-persona")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorPersona {

    private static final Logger logger = LoggerFactory.getLogger(ControladorPersona.class);

    @Autowired
    private ServicioPersona servicioPersona;

    @GetMapping("/persona")
    public ResponseEntity<List<Persona>> listaPersona(){
        List<Persona> listaPersona = servicioPersona.listaPersona();
        logger.info("Lista de Personas obtenidas");
        listaPersona.forEach(persona -> logger.info(persona.toString()));
        return ResponseEntity.ok(listaPersona);
    }
    @GetMapping("/persona/{idPersona}")
    public ResponseEntity<Persona> buscarPersonaPorId(@PathVariable Long idPersona){
        Persona personaEncontrada = servicioPersona
                .buscarPersonaPorId(idPersona);
        if (personaEncontrada != null){
            return ResponseEntity.ok(personaEncontrada);
        }else{
            throw new ResourseNotFoundException("No se encontro la persona con ID: " + idPersona);
        }
    }
    @PostMapping("/persona")
    @ResponseStatus(HttpStatus.CREATED)
    public Persona guardarPersona(@RequestBody Persona persona){
        logger.info("Persona Agregada: "+persona);
        return servicioPersona.guardarPersona(persona);
    }
    @PutMapping("/persona/{idPersona}")
    public ResponseEntity<Persona> actualizarPersona(
            @PathVariable Long idPersona,
            @RequestBody Persona persona){
        Persona personaEncontrada = servicioPersona.buscarPersonaPorId(idPersona);
        if (personaEncontrada != null){
            personaEncontrada.setNombre(persona.getNombre());
            personaEncontrada.setApellido(persona.getApellido());
            personaEncontrada.setDireccion(persona.getDireccion());
            personaEncontrada.setTelefono(persona.getTelefono());
            Persona personaActualizada = servicioPersona.guardarPersona(personaEncontrada);
            return ResponseEntity.ok(personaActualizada);
        }else {
            throw new ResourseNotFoundException("Recurso no encontrado con ID: "+ idPersona);
        }
    }

    @DeleteMapping("/persona/{idPersona}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarPersona(@PathVariable Long idPersona){
        Persona persona = this.servicioPersona.buscarPersonaPorId(idPersona);
        if (persona == null){
            throw new ResourseNotFoundException("Id no encontradi: " + idPersona);
        }
        this.servicioPersona.eliminarPersona(idPersona);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Persona eliminada",Boolean.TRUE);
        return  ResponseEntity.ok(respuesta);
    }
}
