package com.invorg.repositorios;

import com.invorg.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioUsuario extends JpaRepository<Usuario,Long> {
}
