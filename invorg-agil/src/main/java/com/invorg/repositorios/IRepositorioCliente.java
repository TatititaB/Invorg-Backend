package com.invorg.repositorios;

import com.invorg.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepositorioCliente extends JpaRepository<Cliente, Long> {
}
