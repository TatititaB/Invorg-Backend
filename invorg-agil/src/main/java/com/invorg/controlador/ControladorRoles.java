package com.invorg.controlador;

import com.invorg.entidades.Roles;
import com.invorg.excepciones.ResourseNotFoundException;
import com.invorg.servicios.ServicioRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.Role;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api-roles")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorRoles {

    private static final Logger logger = LoggerFactory.getLogger(ControladorRoles.class);

    @Autowired
    private ServicioRoles servicioRoles;

    @GetMapping("/roles")
    public ResponseEntity<List<Roles>> listaRoles(){
        List<Roles> listaRoles = servicioRoles.listaRoles();
        logger.info("Lista de Roles obtenidos");
        listaRoles.forEach(roles -> logger.info(roles.toString()));
        return ResponseEntity.ok(listaRoles);
    }

    @GetMapping("/roles/{idRoles}")
    public ResponseEntity<Roles> buscarRolesPorId(@PathVariable Long idRoles){
        Roles rolesEncontrados = servicioRoles.buscarRolesPorId(idRoles);
        if (rolesEncontrados != null){
            return ResponseEntity.ok(rolesEncontrados);
        }else {
            throw new ResourseNotFoundException("No se encontro el rol con ID: "+ idRoles);
        }
    }

    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public Roles guardarRoles(@RequestBody Roles roles){
        logger.info("Rol agregado: "+ roles);
        return servicioRoles.guardarRol(roles);
    }

    @PutMapping("/roles/{idRoles}")
    public ResponseEntity<Roles> actualizarRol(
            @PathVariable Long idRol,
            @RequestBody Roles roles){
        Roles rolesEncontrados = servicioRoles.buscarRolesPorId(idRol);
        if (rolesEncontrados != null){
            rolesEncontrados.setNombre(roles.getNombre());
            Roles rolesActualizados = servicioRoles.guardarRol(rolesEncontrados);
            return ResponseEntity.ok(rolesActualizados);
        }else {
            throw new ResourseNotFoundException("Recurso no encontrado con ID: "+ idRol);
        }
    }

    @DeleteMapping("/roles/{idRoles}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarRol(@PathVariable Long idRol){
        Roles roles = this.servicioRoles.buscarRolesPorId(idRol);
        if (roles == null){
            throw new ResourseNotFoundException("Id no encontrado: "+ idRol);
        }
        this.servicioRoles.eliminarRol(idRol);

        Map<String,Boolean> respuesta = new HashMap<>();
        respuesta.put("Rol eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
