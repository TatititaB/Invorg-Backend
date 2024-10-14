package com.invorg.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;
    @Column(nullable = false, length = 50)
    private int metrajeMaterial;
    @Column(unique = true, nullable = false, length = 50)
    private String facturaProveedor;
    @Column(nullable = false, length = 50)
    private LocalDate fechaFacturaProveedor;
    @ManyToOne(targetEntity = Material.class)
    @JoinColumn(name = "fk_id_material", referencedColumnName = "idMaterial")
    private Material material;
    @ManyToOne(targetEntity = Proveedor.class)
    @JoinColumn(name = "fk_id_proveedor", referencedColumnName = "idProveedor")
    private Proveedor proveedor;
}
