package com.invorg.repositorios;

import com.invorg.entidades.ReporteAverias;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioReporteAverias extends JpaRepository<ReporteAverias,Long> {
}
