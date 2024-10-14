package com.invorg.servicios;

import com.invorg.entidades.ReporteAverias;

import java.util.List;

public interface IServicioReporteAverias {

    List<ReporteAverias> listaReporteAverias();
    ReporteAverias buscarReporteAveriasPorId(Long idReporteAverias);

    ReporteAverias guardarReporteAverias(ReporteAverias reporteAverias);

    void eliminarReporteAverias(Long idReporteAverias);
}
