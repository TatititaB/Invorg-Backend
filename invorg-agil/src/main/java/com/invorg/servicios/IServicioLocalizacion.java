package com.invorg.servicios;

import com.invorg.entidades.Localizacion;

import java.util.List;

public interface IServicioLocalizacion {

    List<Localizacion> listaLocalizacion();

    Localizacion buscarLocalizacionPorId(Long idLocalizacion);

    Localizacion guardarLocalizacion(Localizacion localizacion);

    void eliminarLocalizacion(Long idLocalizacion);
}
