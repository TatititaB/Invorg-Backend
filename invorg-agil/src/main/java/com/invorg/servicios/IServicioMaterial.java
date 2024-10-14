package com.invorg.servicios;

import com.invorg.entidades.Material;

import java.util.List;

public interface IServicioMaterial {

    List<Material> listaMaterial();

    Material buscarMaterialPorId(Long idMaterial);

    Material guardarMaterial(Material material);
    void eliminarMaterial(Long idMaterrial);

}
