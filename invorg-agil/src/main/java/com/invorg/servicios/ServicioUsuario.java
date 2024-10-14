package com.invorg.servicios;

import com.invorg.entidades.Usuario;
import com.invorg.repositorios.IRepositorioRoles;
import com.invorg.repositorios.IRepositorioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioUsuario implements IServicioUsuario{
    @Autowired
    private IRepositorioUsuario iRepositorioUsuario;
    @Override
    public List<Usuario> listaUsuarios() {
        return this.iRepositorioUsuario.findAll();
    }

    @Override
    public Usuario buscarUsuarioPorId(Long idUsuario) {
        return this.iRepositorioUsuario.findById(idUsuario).orElse(null);
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
        return this.iRepositorioUsuario.save(usuario);
    }

    @Override
    public void eliminarUsuario(Long idUsuario) {
        this.iRepositorioUsuario.deleteById(idUsuario);

    }
}
