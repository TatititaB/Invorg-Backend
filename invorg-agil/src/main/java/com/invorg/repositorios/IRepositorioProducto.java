package com.invorg.repositorios;

import com.invorg.entidades.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioProducto extends JpaRepository<Producto,Long> {
}
