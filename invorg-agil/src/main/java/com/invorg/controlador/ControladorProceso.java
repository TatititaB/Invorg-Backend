package com.invorg.controlador;

import com.invorg.entidades.Proceso;
import com.invorg.excepciones.ResourseNotFoundException;
import com.invorg.servicios.ServicioProceso;
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
@RequestMapping("api-proceso")
@CrossOrigin(origins = "http://localhost:4200/")
public class  ControladorProceso {

    private static final Logger logger = LoggerFactory.getLogger(ControladorProceso.class);

    @Autowired
    private ServicioProceso servicioProceso;

    @GetMapping("/proceso")
    public ResponseEntity<List<Proceso>> listaProceso(){
        List<Proceso> listaProceso = servicioProceso.listaProceso();
        logger.info("Lista de procesos obtenidos");
        listaProceso.forEach(proceso -> logger.info(proceso.toString()));
        return ResponseEntity.ok(listaProceso);
    }
    @GetMapping("/proceso/{idProceso}")
    public ResponseEntity<Proceso> buscarProcesoPorId(@PathVariable Long idProceso){
        Proceso procesoEncontrado = servicioProceso.buscarProcesoPorId(idProceso);
        if (procesoEncontrado != null){
            return ResponseEntity.ok(procesoEncontrado);
        }else {
            throw new ResourseNotFoundException("No se encontro el proceso con ID: "+ idProceso);
        }
    }
    @PostMapping("/proceso")
    @ResponseStatus(HttpStatus.CREATED)
    public Proceso guardarProceso(@RequestBody Proceso proceso){
        logger.info("proceso agregado: "+ proceso);
        return servicioProceso.guardarProceso(proceso);
    }
    @PutMapping("/proceso/{idProceso}")
    public ResponseEntity<Proceso> actualizarProceso(
            @PathVariable Long idProceso,
            @RequestBody Proceso proceso){
        Proceso procesoEncontrado = servicioProceso.buscarProcesoPorId(idProceso);
        if (procesoEncontrado !=null){
            procesoEncontrado.setEspecificacionProceso(proceso.getEspecificacionProceso());
            procesoEncontrado.setTipoTrabajo(proceso.getTipoTrabajo());
            Proceso procesoActualizado = servicioProceso.guardarProceso(procesoEncontrado);
            return ResponseEntity.ok(procesoActualizado);
        }else {
            throw new ResourseNotFoundException("Recurso no encontrado con ID: "+ idProceso);
        }
    }

    @DeleteMapping("/proceso/{idProceso}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, Boolean>> eliminarProceso(@PathVariable Long idProceso){
        Proceso proceso = this.servicioProceso.buscarProcesoPorId(idProceso);
        if (proceso == null){
            throw new ResourseNotFoundException("Id no encontrado: "+ idProceso);
        }
        this.servicioProceso.eliminarProceso(idProceso);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Proceso eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
