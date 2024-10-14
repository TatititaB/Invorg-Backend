package com.invorg.servicios;

import com.invorg.entidades.Proveedor;

import java.util.List;

public interface IServicioProveedor {
    List<Proveedor> listaProveedores();

    Proveedor buscarProveedorPorId(Long idProveedor);

    Proveedor guardarProveedor(Proveedor proveedor);

    void eliminarProveedor(Long id);
}
