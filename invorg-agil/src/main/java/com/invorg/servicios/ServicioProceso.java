package com.invorg.servicios;

import com.invorg.entidades.Proceso;
import com.invorg.repositorios.IRepositorioProceso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioProceso implements IServicioProceso {

    @Autowired
    private IRepositorioProceso iRepositorioProceso;
    @Override
    public List<Proceso> listaProceso() {
        return this.iRepositorioProceso.findAll();
    }

    @Override
    public Proceso buscarProcesoPorId(Long idProceso) {
        return this.iRepositorioProceso.findById(idProceso).orElse(null);
    }

    @Override
    public Proceso guardarProceso(Proceso proceso) {
        return this.iRepositorioProceso.save(proceso);
    }

    @Override
    public void eliminarProceso(Long idProducto) {
        this.iRepositorioProceso.deleteById(idProducto);
    }
}
