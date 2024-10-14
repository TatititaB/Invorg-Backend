package com.invorg.servicios;

import com.invorg.entidades.Roles;
import com.invorg.repositorios.IRepositorioRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioRoles implements IServicioRoles{
    @Autowired
    private IRepositorioRoles iRepositorioRoles;
    @Override
    public List<Roles> listaRoles() {
        return this.iRepositorioRoles.findAll();
    }

    @Override
    public Roles buscarRolesPorId(Long idRol) {
        return this.iRepositorioRoles.findById(idRol).orElse(null);
    }

    @Override
    public Roles guardarRol(Roles roles) {
        return this.iRepositorioRoles.save(roles);
    }

    @Override
    public void eliminarRol(Long idRol) {
        this.iRepositorioRoles.deleteById(idRol);

    }
}
