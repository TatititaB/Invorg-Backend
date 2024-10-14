package com.invorg.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    @Column(nullable = false, length = 50)
    private String nombre;
    @Column(nullable = false, length = 50)
    private String apellido;
    @Column(nullable = false, length = 15)
    private Integer telefono;
    @Column(nullable = false, length = 150)
    private String direccion;
    @ManyToOne(targetEntity = Documento.class)
    @JoinColumn(name = "fk_id_documento", referencedColumnName = "idDocumento")
    private Documento documento;
    @ManyToOne(targetEntity = Localizacion.class)
    @JoinColumn(name = "fk_id_localizacion", referencedColumnName = "idLocalizacion")
    private Localizacion localizacion;
}
