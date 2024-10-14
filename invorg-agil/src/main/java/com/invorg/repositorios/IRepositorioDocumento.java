package com.invorg.repositorios;

import com.invorg.entidades.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioDocumento extends JpaRepository<Documento,Long> {
}
