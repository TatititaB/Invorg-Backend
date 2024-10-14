package com.invorg.repositorios;

import com.invorg.entidades.Material;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioMaterial extends JpaRepository<Material,Long> {
}
