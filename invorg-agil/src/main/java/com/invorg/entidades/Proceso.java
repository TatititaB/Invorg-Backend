package com.invorg.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Proceso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProceso;
    @Column(nullable = false, length = 50)
    private String tipoTrabajo;
    @Column(nullable = false, length = 250)
    private String especificacionProceso;
}
