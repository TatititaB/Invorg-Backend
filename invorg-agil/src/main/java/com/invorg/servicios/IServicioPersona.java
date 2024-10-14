package com.invorg.servicios;

import com.invorg.entidades.Persona;

import java.util.List;

public interface IServicioPersona {
    List<Persona> listaPersona();

    Persona buscarPersonaPorId(Long idPersona);

    Persona guardarPersona(Persona persona);

    void eliminarPersona(Long idPersona);

}
