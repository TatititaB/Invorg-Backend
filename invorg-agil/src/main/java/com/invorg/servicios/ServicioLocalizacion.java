package com.invorg.servicios;

import com.invorg.entidades.Localizacion;
import com.invorg.repositorios.IRepositorioLocalizacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioLocalizacion implements IServicioLocalizacion{
    @Autowired
    private IRepositorioLocalizacion iRepositorioLocalizacion;
    @Override
    public List<Localizacion> listaLocalizacion() {
        return this.iRepositorioLocalizacion.findAll();
    }

    @Override
    public Localizacion buscarLocalizacionPorId(Long idLocalizacion) {
        return this.iRepositorioLocalizacion.findById(idLocalizacion).orElse(null);
    }

    @Override
    public Localizacion guardarLocalizacion(Localizacion localizacion) {
        return this.iRepositorioLocalizacion.save(localizacion);
    }

    @Override
    public void eliminarLocalizacion(Long idLocalizacion) {
        this.iRepositorioLocalizacion.deleteById(idLocalizacion);
    }
}
