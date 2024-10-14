package com.invorg.repositorios;

import com.invorg.entidades.Localizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioLocalizacion extends JpaRepository<Localizacion,Long> {
}
