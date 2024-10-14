package com.invorg.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ReporteAverias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReporte;
    @Column(nullable = false,length = 250)
    private String detalles;
    @ManyToOne(targetEntity = Tarea.class)
    @JoinColumn(name = "fk_id_tarea", referencedColumnName = "idTarea")
    private Tarea tarea;

}
