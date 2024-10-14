package com.invorg.servicios;

import com.invorg.entidades.Persona;
import com.invorg.repositorios.IRepositorioPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioPersona implements IServicioPersona{
    @Autowired
    private IRepositorioPersona iRepositorioPersona;
    @Override
    public List<Persona> listaPersona() {
        return this.iRepositorioPersona.findAll();
    }

    @Override
    public Persona buscarPersonaPorId(Long idPersona) {
        return this.iRepositorioPersona.findById(idPersona).orElse(null);
    }

    @Override
    public Persona guardarPersona(Persona persona) {
        return this.iRepositorioPersona.save(persona);
    }

    @Override
    public void eliminarPersona(Long idPersona) {
        this.iRepositorioPersona.deleteById(idPersona);
    }
}
