package com.invorg.servicios;

import com.invorg.entidades.Roles;

import java.util.List;

public interface IServicioRoles {

    List<Roles> listaRoles();
    Roles buscarRolesPorId(Long idRol);

    Roles guardarRol(Roles roles);

    void eliminarRol(Long idRol);
}
