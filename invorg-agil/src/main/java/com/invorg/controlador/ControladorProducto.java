package com.invorg.controlador;

import com.invorg.entidades.Producto;
import com.invorg.excepciones.ResourseNotFoundException;
import com.invorg.servicios.ServicioProducto;
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
@RequestMapping("api-producto")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorProducto {

    private static final Logger logger = LoggerFactory.getLogger(ControladorProducto.class);

    @Autowired
    private ServicioProducto servicioProducto;

    @GetMapping("/producto")
    public ResponseEntity<List<Producto>>listaProducto(){
        List<Producto> listaProducto = servicioProducto.listaProducto();
        logger.info("Lista de productos obtenidos ");

        listaProducto.forEach(producto -> logger.info(producto.toString()));
        return ResponseEntity.ok(listaProducto);
    }
    @GetMapping("/producto/{idProducto}")
    public ResponseEntity<Producto> buscarProductoPorId(@PathVariable Long idProducto){
        Producto productoEncontrado = servicioProducto.buscarProductoPorId(idProducto);
        if ( productoEncontrado != null){
            return ResponseEntity.ok(productoEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontro el coordinador con ID: "+ idProducto);
        }
    }
    @PostMapping("/producto")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto guardarProducto(@RequestBody Producto producto){
        logger.info("Coordinador agregado: " + producto);
        return servicioProducto.guardarProducto(producto);
    }

    @PutMapping("/producto/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
        Producto productoEncontrado = servicioProducto.buscarProductoPorId(id);
        if (productoEncontrado != null){
            productoEncontrado.setFacturaProveedor(producto.getFacturaProveedor());
            productoEncontrado.setMetrajeMaterial(producto.getMetrajeMaterial());
            productoEncontrado.setFechaFacturaProveedor(producto.getFechaFacturaProveedor());
            Producto productoActualizado = servicioProducto.guardarProducto(productoEncontrado);
            return ResponseEntity.ok(productoActualizado);
        } else {
            throw new ResourseNotFoundException(" Resurso no encontrado con ID: "+id);
        }
    }

    @DeleteMapping("/producto/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String,Boolean>> eliminarProducto(@PathVariable Long id){
        Producto producto = this.servicioProducto.buscarProductoPorId(id);
        if (producto == null) {
            throw new ResourseNotFoundException("Id no encontrado: "+id);
        }
        this.servicioProducto.eliminarProducto(id);

        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("Producto eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }

}
