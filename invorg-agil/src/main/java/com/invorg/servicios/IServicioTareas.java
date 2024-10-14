package com.invorg.servicios;

import com.invorg.entidades.Tarea;

import java.util.List;

public interface IServicioTareas {
    List<Tarea> listaTareas();

    Tarea buscarTareaPorId(Long idTarea);

    Tarea guardarTarea(Tarea tarea);

    void eliminarTarea(Long idTarea);
}
