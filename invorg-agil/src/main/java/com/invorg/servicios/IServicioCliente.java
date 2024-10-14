package com.invorg.servicios;

import com.invorg.entidades.Cliente;

import java.util.List;

public interface IServicioCliente {
    List<Cliente> listaCliente();

    Cliente buscarClientePorId(Long idCliente);

    Cliente guardarCliente(Cliente cliente);

    void eliminarCoordinador(Long idCliente);
}
