package com.invorg.controlador;

import com.fasterxml.jackson.databind.ObjectReader;
import com.invorg.entidades.ReporteAverias;
import com.invorg.excepciones.ResourseNotFoundException;
import com.invorg.servicios.ServicioReporteAverias;
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
@RequestMapping("api-reporteAverias")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorReporteAverias {

    private static final Logger logger = LoggerFactory.getLogger(ControladorReporteAverias.class);

    @Autowired
    private ServicioReporteAverias servicioReporteAverias;

    @GetMapping("/reporteAverias")
    public ResponseEntity<List<ReporteAverias>> listaReporteAverias(){
        List<ReporteAverias> listaReporteAverias = servicioReporteAverias.listaReporteAverias();
        logger.info("Lista de Reporte de Averias Obtenidas");
        listaReporteAverias.forEach(reporteAverias -> logger.info(reporteAverias.toString()));
        return ResponseEntity.ok(listaReporteAverias);
    }

    @GetMapping("/reporteAverias/{idReporteAverias}")
    public ResponseEntity<ReporteAverias> buscarReporteAveriasPorId(@PathVariable Long idReporteAverias){

        ReporteAverias reporteAveriasEncontradas = servicioReporteAverias.buscarReporteAveriasPorId(idReporteAverias);
        if (reporteAveriasEncontradas != null){
            return ResponseEntity.ok(reporteAveriasEncontradas);
        }else {
            throw new ResourseNotFoundException("No se encontro el Reporte de Averia con ID: "+ idReporteAverias);
        }
    }

    @PostMapping("/reporteAverias")
    @ResponseStatus(HttpStatus.CREATED)
    public ReporteAverias guardarReporteAverias(@RequestBody ReporteAverias reporteAverias){
        logger.info("Reporte Averias agregado: "+ reporteAverias);
        return servicioReporteAverias.guardarReporteAverias(reporteAverias);
    }

    @PutMapping("/reporteAverias/{idReporteAverias}")
    public ResponseEntity<ReporteAverias> actualizarReporteAverias(
            @PathVariable Long idReporteAverias,
            @RequestBody ReporteAverias reporteAverias){
        ReporteAverias reporteAveriasEncontrada = servicioReporteAverias.buscarReporteAveriasPorId(idReporteAverias);
        if (reporteAveriasEncontrada != null){
            reporteAveriasEncontrada.setDetalles(reporteAverias.getDetalles());
            ReporteAverias reporteAveriasActualizadas = servicioReporteAverias.guardarReporteAverias(reporteAverias);
            return ResponseEntity.ok(reporteAveriasActualizadas);
        }else {
            throw new ResourseNotFoundException("Recurso no encontrado con ID: "+ idReporteAverias);
        }
    }

    @DeleteMapping("/reporteAverias/{idReporteAverias}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarReporteAverias(@PathVariable Long idReporteAverias) {
        ReporteAverias reporteAverias = this.servicioReporteAverias.buscarReporteAveriasPorId(idReporteAverias);
        if (reporteAverias == null){
            throw new ResourseNotFoundException("Id no encontrado: "+idReporteAverias);
        }
        this.servicioReporteAverias.eliminarReporteAverias(idReporteAverias);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Reporte Averias eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
