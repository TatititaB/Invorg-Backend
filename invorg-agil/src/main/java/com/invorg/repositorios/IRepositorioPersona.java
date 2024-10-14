package com.invorg.repositorios;

import com.invorg.entidades.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioPersona extends JpaRepository<Persona,Long> {
}
