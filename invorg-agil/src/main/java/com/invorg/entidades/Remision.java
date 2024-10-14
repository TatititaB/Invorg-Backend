package com.invorg.entidades;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Remision {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRemision;
    @Column(nullable = false,length = 5)
    private int cantidadProductos;
    @Column(nullable = false,length = 20)
    private LocalDate fecha_remision;
    @Column(nullable = false,length = 5)
    private String numeroRemision;
    @ManyToOne(targetEntity = Cliente.class)
    @JoinColumn(name = "fk_id_cliente", referencedColumnName = "idCliente")
    private Cliente cliente;
    @ManyToOne(targetEntity = Usuario.class)
    @JoinColumn(name = "fk_id_usuario", referencedColumnName = "idUsuario")
    private Usuario usuario;
    @ManyToOne(targetEntity = Tarea.class)
    @JoinColumn(name = "fk_id_tarea", referencedColumnName = "idTarea")
    private Tarea tarea;
}
