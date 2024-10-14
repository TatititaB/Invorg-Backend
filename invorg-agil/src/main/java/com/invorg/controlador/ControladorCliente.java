package com.invorg.controlador;

import com.invorg.entidades.Cliente;
import com.invorg.excepciones.ResourseNotFoundException;
import com.invorg.servicios.ServicioCliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.lang.model.util.Elements;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api-cliente")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorCliente {
    private static final Logger logger = LoggerFactory.getLogger(ControladorCliente.class);

    @Autowired
    private ServicioCliente servicioCliente;

    @GetMapping("/cliente")
    public ResponseEntity<List<Cliente>> listaCliente(){
        List<Cliente> listaClientes = servicioCliente.listaCliente();
        logger.info("lista de clientes obtenidos");
        listaClientes.forEach(cliente -> logger.info(cliente.toString()));
        return ResponseEntity.ok(listaClientes);
    }
    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<Cliente> buscarClientePorId(@PathVariable Long idCliente){
        Cliente clienteEncontrado = servicioCliente.buscarClientePorId(idCliente);
        if (clienteEncontrado != null){
            return ResponseEntity.ok(clienteEncontrado);
        }else{
            throw new ResourseNotFoundException("No se encontro el cliente con ID: "+ idCliente);
        }
    }

    @PostMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente guardarCliente(@RequestBody Cliente cliente){
        logger.info("Cliente agregado: " + cliente);
        return servicioCliente.guardarCliente(cliente);
    }

    @DeleteMapping("/cliente/{idCliente}")
    public ResponseEntity<Map<String, Boolean>> eliminarCoordinador(@PathVariable Long idCliente){
        Cliente cliente = servicioCliente.buscarClientePorId(idCliente);
        if(cliente == null){
            servicioCliente.eliminarCoordinador(idCliente);
            Map<String, Boolean> respuesta = new HashMap<>();
            respuesta.put("Eliminado", Boolean.TRUE);
            return  ResponseEntity.ok(respuesta);
        } else {
            throw new ResourseNotFoundException("Id no encontrado: " + idCliente);
        }
    }
}
