package com.invorg.controlador;

import com.invorg.entidades.Material;
import com.invorg.excepciones.ResourseNotFoundException;
import com.invorg.servicios.ServicioMaterial;
import jakarta.persistence.Entity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api-material")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorMaterial {

    private static final Logger logger = LoggerFactory.getLogger(ControladorMaterial.class);

    @Autowired
    private ServicioMaterial servicioMaterial;

    @GetMapping("/material")
    public ResponseEntity<List<Material>> listaMateria(){
        List<Material> listaMaterial = servicioMaterial.listaMaterial();
        logger.info("Lista de materiales obtenidos: ");
        listaMaterial.forEach(material -> logger.info(material.toString()));
        return ResponseEntity.ok(listaMaterial);
    }
    @GetMapping("/material/{idMaterial}")
    public ResponseEntity<Material> buscarMaterialPorId(@PathVariable Long idMaterial){
        Material materialEncontrado = servicioMaterial.buscarMaterialPorId(idMaterial);
        if (materialEncontrado != null){
            return ResponseEntity.ok(materialEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontro material con ID: "+ idMaterial);
        }
    }
    @PostMapping("/material")
    @ResponseStatus(HttpStatus.CREATED)
    public Material guardarMaterial(@RequestBody Material material){
        logger.info(("Material agregado: "+ material));
        return  servicioMaterial.guardarMaterial(material);
    }

    @PutMapping("/material/{idMaterial}")
    public ResponseEntity<Material> actualizarMaterial(
            @PathVariable Long idMaterial,
            @RequestBody Material material){
        Material materialEncontrado = servicioMaterial.buscarMaterialPorId(idMaterial);
        if (materialEncontrado != null){
            materialEncontrado.setAnchoMaterial(material.getAnchoMaterial());
            materialEncontrado.setTipoMaterial(material.getTipoMaterial());
            materialEncontrado.setColor(material.getColor());
            materialEncontrado.setTonalidad(material.getTonalidad());
            Material materialActualizado = servicioMaterial.guardarMaterial(materialEncontrado);
            return ResponseEntity.ok(materialActualizado);
        }else{
            throw new ResourseNotFoundException("Recurso no encontrado con ID: "+idMaterial);
        }
    }
    @DeleteMapping("/material/{idMaterial}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Map<String, Boolean>> eliminarMaterial(@PathVariable Long idMaterial) {
        Material material = this.servicioMaterial.buscarMaterialPorId(idMaterial);
        if (material ==null) {
            throw  new ResourseNotFoundException("Id no encontrado: "+ idMaterial);
        }
        this.servicioMaterial.eliminarMaterial(idMaterial);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Material eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
