package com.invorg.servicios;

import com.invorg.entidades.Material;
import com.invorg.repositorios.IRepositorioMaterial;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServicioMaterial implements IServicioMaterial{
    @Autowired
    private IRepositorioMaterial iRepositorioMaterial;
    @Override
    public List<Material> listaMaterial() {
        return this.iRepositorioMaterial.findAll();
    }

    @Override
    public Material buscarMaterialPorId(Long idMaterial) {
        return this.iRepositorioMaterial.findById(idMaterial).orElse(null);
    }

    @Override
    public Material guardarMaterial(Material material) {
        return this.iRepositorioMaterial.save(material);
    }

    @Override
    public void eliminarMaterial(Long idMaterrial) {
        this.iRepositorioMaterial.deleteById(idMaterrial);
    }
}
