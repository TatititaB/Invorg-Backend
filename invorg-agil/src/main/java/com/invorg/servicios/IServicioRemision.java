package com.invorg.servicios;

import com.invorg.entidades.Remision;

import java.util.List;

public interface IServicioRemision {

    List<Remision> listaRemision();

    Remision buscarRemisionPorId(Long idRemision);

    Remision guardarRemision(Remision remision);

    void eliminarRemision(Long idRemision);
}
