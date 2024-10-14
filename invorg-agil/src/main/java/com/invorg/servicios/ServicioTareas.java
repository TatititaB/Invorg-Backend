package com.invorg.servicios;

import com.invorg.entidades.Tarea;
import com.invorg.repositorios.IRepositorioTareas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioTareas implements IServicioTareas{
    @Autowired
    private IRepositorioTareas iRepositorioTareas;
    @Override
    public List<Tarea> listaTareas() {
        return this.iRepositorioTareas.findAll();
    }

    @Override
    public Tarea buscarTareaPorId(Long idTarea) {
        return this.iRepositorioTareas.findById(idTarea).orElse(null);
    }

    @Override
    public Tarea guardarTarea(Tarea tarea) {
        return this.iRepositorioTareas.save(tarea);
    }

    @Override
    public void eliminarTarea(Long idTarea) {
        this.iRepositorioTareas.deleteById(idTarea);
   }
}
