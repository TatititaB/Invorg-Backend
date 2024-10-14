package com.invorg.repositorios;

import com.invorg.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioProveedor extends JpaRepository<Proveedor,Long> {
}
