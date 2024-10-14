package com.invorg.controlador;

import com.invorg.entidades.Documento;
import com.invorg.excepciones.ResourseNotFoundException;
import com.invorg.servicios.ServicioDocumento;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping("api-documento")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorDocumento {

    private static final Logger logger = LoggerFactory.getLogger(ControladorDocumento.class);

    @Autowired
    private ServicioDocumento servicioDocumento;

    @GetMapping("/documento")
    public ResponseEntity<List<Documento>> listaDocumento(){
        List<Documento> listaDocumento = servicioDocumento.listaDocumento();
        logger.info("Lista de Documentos obtenidos");
        listaDocumento.forEach(documento -> logger.info(documento.toString()));
        return ResponseEntity.ok(listaDocumento);
    }

    @GetMapping("/documento/{idDocumento}")
    public ResponseEntity<Documento> buscarDocumentoPorId(@PathVariable Long idDocumento){
        Documento documentoEncontrado = servicioDocumento.buscarDocumentoPorId(idDocumento);
        if (documentoEncontrado !=null){
            return ResponseEntity.ok(documentoEncontrado);
        }else {
            throw new ResourseNotFoundException("No se encontro el Documento con ID: " + idDocumento);
        }
    }
    @PostMapping("/documento")
    @ResponseStatus(HttpStatus.CREATED)
    public Documento guardarDocumento(@RequestBody Documento documento){
        logger.info("Documento Agregado: " + documento);
        return servicioDocumento.guardarDocumento(documento);
    }

    @PutMapping("/coordinador/{idDocumento}")
    public ResponseEntity<Documento> actualizarDocumento(
            @PathVariable Long idDocumento,
            @RequestBody Documento documento){
        Documento documentoEncontrado = servicioDocumento.buscarDocumentoPorId(idDocumento);
        if (documentoEncontrado !=null){
            documentoEncontrado.setNumDocumento(documento.getNumDocumento());
            documentoEncontrado.setTipoDocumento(documento.getTipoDocumento());
            Documento documentoActualizado = servicioDocumento.guardarDocumento(documentoEncontrado);
            return ResponseEntity.ok(documentoActualizado);
        }else{
            throw  new ResourseNotFoundException("Recurso no encontrado con ID: "+ idDocumento);
        }
    }
}
