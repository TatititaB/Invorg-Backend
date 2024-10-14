package com.invorg.controlador;

import com.invorg.entidades.Tarea;
import com.invorg.excepciones.ResourseNotFoundException;
import com.invorg.servicios.ServicioTareas;
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
@RequestMapping("api-tarea")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorTarea {

    private static final Logger logger = LoggerFactory.getLogger(ControladorTarea.class);

    @Autowired
    private ServicioTareas servicioTareas;

    @GetMapping("/tarea")
    public ResponseEntity<List<Tarea>> listaTarea(){
        List<Tarea> listaTareas = servicioTareas.listaTareas();
        logger.info("Lista de Tareas obtenidas");
        listaTareas.forEach(tarea -> logger.info(tarea.toString()));
        return ResponseEntity.ok(listaTareas);
    }

    @GetMapping("/tarea/{idTarea}")
    public ResponseEntity<Tarea> buscarTareaPorId(@PathVariable Long  idTarea){
        Tarea tareaEncontrada = servicioTareas.buscarTareaPorId(idTarea);
        if (tareaEncontrada != null){
            return ResponseEntity.ok(tareaEncontrada);
        }else{
            throw new ResourseNotFoundException("No se encontro la tarea con ID: "+ idTarea);
        }
    }
    @PostMapping("/tarea")
    @ResponseStatus(HttpStatus.CREATED)
    public Tarea guardarTarea(@RequestBody Tarea tarea){
        logger.info("Tarea agregada: "+ tarea);
        return servicioTareas.guardarTarea(tarea);
    }

    @DeleteMapping("/tarea/{idTarea}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarTarea(@PathVariable Long idTarea){
        Tarea tarea = this.servicioTareas.buscarTareaPorId(idTarea);
        if (tarea == null){
            throw new ResourseNotFoundException("Id no encontrado: "+idTarea);
        }
        this.servicioTareas.eliminarTarea(idTarea);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Tarea eliminada", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
