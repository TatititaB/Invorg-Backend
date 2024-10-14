package com.invorg.controlador;

import com.invorg.entidades.Usuario;
import com.invorg.excepciones.ResourseNotFoundException;
import com.invorg.servicios.ServicioUsuario;
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
@RequestMapping("api-usuario")
@CrossOrigin(origins = "http://localhost:4200/")
public class ControladorUsuario {

    private static final Logger logger = LoggerFactory.getLogger(ControladorUsuario.class);

    @Autowired
    private ServicioUsuario servicioUsuario;

    @GetMapping("/usuario")
    public ResponseEntity<List<Usuario>> listaUsuario(){
        List<Usuario> listaUsuarios = servicioUsuario.listaUsuarios();
        logger.info("Lista de Usuarios obtenidos");
        listaUsuarios.forEach(usuario -> logger.info(usuario.toString()));
        return ResponseEntity.ok(listaUsuarios);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long idUsuario){
        Usuario usuarioEncontrado = servicioUsuario.buscarUsuarioPorId(idUsuario);
        if (usuarioEncontrado!=null){
            return ResponseEntity.ok(usuarioEncontrado);
        }else {
            throw new ResourseNotFoundException("No se encontro el usuario con ID: "+idUsuario);
        }
    }

    @PostMapping("/usuario")
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario guardarUsuario(@RequestBody Usuario usuario){
        logger.info("Usuario Agregado: "+ usuario);
        return servicioUsuario.guardarUsuario(usuario);
    }

    @PutMapping("/usuario/{idUsuario}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable Long idUsuario,
            @RequestBody Usuario usuario){
        Usuario usuarioEncontrado = servicioUsuario.buscarUsuarioPorId(idUsuario);
        if (usuarioEncontrado != null){
            usuarioEncontrado.setNombre(usuario.getNombre());
            usuarioEncontrado.setClave(usuario.getClave());
            Usuario usuarioActualizado = servicioUsuario.guardarUsuario(usuarioEncontrado);
            return ResponseEntity.ok(usuarioActualizado);
        }else {
            throw new ResourseNotFoundException("Recurso no encontrado con ID: "+ idUsuario);
        }

    }

    @DeleteMapping("/usuario/{idUsuario}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Map<String, Boolean>> eliminarUsuario(@PathVariable Long idUsuario) {
        Usuario usuario = this.servicioUsuario.buscarUsuarioPorId(idUsuario);
        if (usuario == null){
            throw new ResourseNotFoundException("Id no encontrado: "+idUsuario);
        }
        this.servicioUsuario.eliminarUsuario(idUsuario);

        Map<String,Boolean> respuesta = new HashMap<>();
        respuesta.put("Usuario eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
