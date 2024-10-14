package com.invorg.entidades;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Localizacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLocalizacion;
    @Column(length = 55)
    private String pais;
    @Column(length = 55)
    private String ciudad;
    @Column(length = 55)
    private String departamento;
}
