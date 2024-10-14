package com.invorg.servicios;

import com.invorg.entidades.Producto;
import com.invorg.repositorios.IRepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioProducto implements IServicioProducto{
    @Autowired
    private IRepositorioProducto iRepositorioProducto;
    @Override
    public List<Producto> listaProducto() {
        return this.iRepositorioProducto.findAll();
    }

    @Override
    public Producto buscarProductoPorId(Long idProducto) {
        return this.iRepositorioProducto.findById(idProducto).orElse(null);
    }

    @Override
    public Producto guardarProducto(Producto producto) {
        return this.iRepositorioProducto.save(producto);
    }

    @Override
    public void eliminarProducto(Long idProducto) {
        this.iRepositorioProducto.deleteById(idProducto);

    }
}
