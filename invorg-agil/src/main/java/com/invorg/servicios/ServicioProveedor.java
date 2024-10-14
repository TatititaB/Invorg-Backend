package com.invorg.servicios;

import com.invorg.entidades.Proveedor;
import com.invorg.repositorios.IRepositorioProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioProveedor implements IServicioProveedor{
    @Autowired
    private IRepositorioProveedor iRepositorioProveedor;
    @Override
    public List<Proveedor> listaProveedores() {
        return this.iRepositorioProveedor.findAll();
    }

    @Override
    public Proveedor buscarProveedorPorId(Long idProveedor) {
        return this.iRepositorioProveedor.findById(idProveedor).orElse(null);
    }

    @Override
    public Proveedor guardarProveedor(Proveedor proveedor) {
        return this.iRepositorioProveedor.save(proveedor);
    }

    @Override
    public void eliminarProveedor(Long idProveedor) {
        this.iRepositorioProveedor.deleteById(idProveedor);

    }
}
