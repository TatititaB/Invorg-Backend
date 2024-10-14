package com.invorg.servicios;

import com.invorg.entidades.Proceso;

import java.util.List;

public interface IServicioProceso {

    List<Proceso> listaProceso();

    Proceso buscarProcesoPorId(Long idProceso);

    Proceso guardarProceso(Proceso proceso);

    void eliminarProceso(Long idProceso);
}
