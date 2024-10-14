package com.invorg.servicios;

import com.invorg.entidades.Documento;

import java.util.List;

public interface IServicioDocumento {
    List<Documento> listaDocumento();

    Documento buscarDocumentoPorId(Long idDocumento);

    Documento guardarDocumento(Documento documento);

    void eliminarDocumento(Long idDocumento);
}
