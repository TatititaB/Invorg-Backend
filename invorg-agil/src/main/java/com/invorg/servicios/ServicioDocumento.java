package com.invorg.servicios;

import com.invorg.entidades.Documento;
import com.invorg.repositorios.IRepositorioDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioDocumento implements IServicioDocumento {

    @Autowired
    private IRepositorioDocumento iRepositorioDocumento;
    @Override
    public List<Documento> listaDocumento() {
        return this.iRepositorioDocumento.findAll();
    }

    @Override
    public Documento buscarDocumentoPorId(Long idDocumento) {
        return this.iRepositorioDocumento.findById(idDocumento).orElse(null);
    }

    @Override
    public Documento guardarDocumento(Documento documento) {
        return this.iRepositorioDocumento.save(documento);
    }

    @Override
    public void eliminarDocumento(Long idDocumento) {
        this.iRepositorioDocumento.deleteById(idDocumento);

    }
}
