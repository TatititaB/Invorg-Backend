package com.invorg.servicios;

import com.invorg.entidades.Cliente;
import com.invorg.repositorios.IRepositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioCliente implements IServicioCliente{
    @Autowired
    private IRepositorioCliente iRepositorioCliente;

    @Override
    public List<Cliente> listaCliente() {
        return this.iRepositorioCliente.findAll();
    }

    @Override
    public Cliente buscarClientePorId(Long idCliente) {
        return this.iRepositorioCliente.findById(idCliente).orElse(null);
    }

    @Override
    public Cliente guardarCliente(Cliente cliente) {
        return this.iRepositorioCliente.save(cliente);
    }

    @Override
    public void eliminarCoordinador(Long idCliente) {
        this.iRepositorioCliente.deleteById(idCliente);

    }
}
