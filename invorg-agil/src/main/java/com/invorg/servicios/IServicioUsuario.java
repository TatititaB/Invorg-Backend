package com.invorg.servicios;

import com.invorg.entidades.Usuario;

import java.util.List;

public interface IServicioUsuario {
    List<Usuario> listaUsuarios();

    Usuario buscarUsuarioPorId(Long idUsuario);

    Usuario guardarUsuario(Usuario usuario);

    void eliminarUsuario(Long idUsuario);
}
