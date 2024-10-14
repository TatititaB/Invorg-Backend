package com.invorg.repositorios;

import com.invorg.entidades.Proceso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioProceso extends JpaRepository<Proceso,Long> {
}
