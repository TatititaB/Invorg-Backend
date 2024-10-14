package com.invorg.controlador;

import com.invorg.entidades.Proveedor;
import com.invorg.excepciones.ResourseNotFoundException;
import com.invorg.servicios.ServicioProveedor;
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
@RequestMapping("api-proveedor")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorProveedor {

    private static final Logger logger = LoggerFactory.getLogger(ControladorProveedor.class);

    @Autowired
    private ServicioProveedor servicioProveedor;

    @GetMapping("/proveedor")
    public ResponseEntity<List<Proveedor>> listaProveedor(){
        List<Proveedor> listaProveedores = servicioProveedor.listaProveedores();
        logger.info("Lista de Proveedores Obtenidos");
        listaProveedores.forEach(proveedor -> logger.info(proveedor.toString()));
        return ResponseEntity.ok(listaProveedores);
    }
    @GetMapping("/proveedor/{idProveedor}")
    public ResponseEntity<Proveedor> buscarProveedorPorId(@PathVariable Long idProveedor){
        Proveedor proveedorEncontrado = servicioProveedor.buscarProveedorPorId(idProveedor);
        if (proveedorEncontrado != null){
            return  ResponseEntity.ok(proveedorEncontrado);
        }else {
            throw new ResourseNotFoundException("No se encontro el Proveedor con ID: " + idProveedor);
        }
    }
    @PostMapping("/proveedor")
    @ResponseStatus(HttpStatus.CREATED)
    public Proveedor guardarProveedor(@RequestBody Proveedor proveedor){
        logger.info("Proveedor agregado: "+ proveedor);
        return servicioProveedor.guardarProveedor(proveedor);
    }

    @DeleteMapping("/proveedor/{idProveedor}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarProveedor(@PathVariable Long idProveedor){
        Proveedor proveedor = this.servicioProveedor.buscarProveedorPorId(idProveedor);
        if (proveedor == null) {
            throw  new ResourseNotFoundException("Id no encontrado: "+idProveedor);
        }
        this.servicioProveedor.eliminarProveedor(idProveedor);
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Proveedor eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
