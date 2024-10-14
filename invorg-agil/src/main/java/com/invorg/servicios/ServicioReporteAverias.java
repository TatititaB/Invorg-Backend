package com.invorg.servicios;

import com.invorg.entidades.ReporteAverias;
import com.invorg.repositorios.IRepositorioReporteAverias;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioReporteAverias implements IServicioReporteAverias{
    @Autowired
    private IRepositorioReporteAverias iRepositorioReporteAverias;
    @Override
    public List<ReporteAverias> listaReporteAverias() {
        return this.iRepositorioReporteAverias.findAll();
    }

    @Override
    public ReporteAverias buscarReporteAveriasPorId(Long idReporteAverias) {
        return this.iRepositorioReporteAverias.findById(idReporteAverias).orElse(null);
    }

    @Override
    public ReporteAverias guardarReporteAverias(ReporteAverias reporteAverias) {
        return this.iRepositorioReporteAverias.save(reporteAverias);
    }

    @Override
    public void eliminarReporteAverias(Long idReporteAverias) {
        this.iRepositorioReporteAverias.deleteById(idReporteAverias);

    }
}
