package com.invorg.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Tarea {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idTarea;
    @ManyToOne(targetEntity = Remision.class)
    @JoinColumn(name = "fk_id_remision", referencedColumnName = "idRemision")
    private Remision remision;
    @ManyToOne(targetEntity = Proceso.class)
    @JoinColumn(name = "fk_id_proceso", referencedColumnName = "idProceso")
    private Proceso proceso;
    @ManyToOne(targetEntity = Producto.class)
    @JoinColumn(name = "fk_id_producto", referencedColumnName = "idProducto")
    private Producto producto;
}
