package com.invorg.servicios;

import com.invorg.entidades.Remision;
import com.invorg.repositorios.IRepositorioRemision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioRemision implements IServicioRemision{
    @Autowired
    private IRepositorioRemision iRepositorioRemision;
    @Override
    public List<Remision> listaRemision() {
        return this.iRepositorioRemision.findAll();
    }

    @Override
    public Remision buscarRemisionPorId(Long idRemision) {
        return this.iRepositorioRemision.findById(idRemision).orElse(null);
    }

    @Override
    public Remision guardarRemision(Remision remision) {
        return this.iRepositorioRemision.save(remision);
    }

    @Override
    public void eliminarRemision(Long idRemision) {
        this.iRepositorioRemision.deleteById(idRemision);

    }
}
