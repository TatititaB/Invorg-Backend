package com.invorg.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Material {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idMaterial;
    @Column(nullable = false, length = 5)
    private int anchoMaterial;
    @Column(nullable = false, length = 20)
    private String tipoMaterial;
    @Column(nullable = false, length = 20)
    private String tonalidad;
    @Column(nullable = false, length = 20)
    private String color;
}
