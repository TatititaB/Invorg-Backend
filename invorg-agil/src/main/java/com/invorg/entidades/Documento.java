package com.invorg.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Documento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocumento;
    @Column(nullable = false, length = 15, unique = true)
    private Integer numDocumento;
    @Column(nullable = false)
    private String tipoDocumento;
}
