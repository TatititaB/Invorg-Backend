package com.invorg.repositorios;

import com.invorg.entidades.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioTareas extends JpaRepository<Tarea,Long> {
}
