package com.invorg.repositorios;

import com.invorg.entidades.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioRoles extends JpaRepository<Roles,Long> {
}
