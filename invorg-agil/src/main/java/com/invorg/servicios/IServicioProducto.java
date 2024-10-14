package com.invorg.servicios;

import com.invorg.entidades.Producto;

import java.util.List;

public interface IServicioProducto {

    List<Producto> listaProducto();

    Producto buscarProductoPorId(Long idProducto);

    Producto guardarProducto(Producto producto);

    void eliminarProducto(Long idProducto);
}
