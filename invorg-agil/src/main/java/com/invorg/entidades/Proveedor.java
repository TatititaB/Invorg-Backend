package com.invorg.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Proveedor {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idProveedor;
    @ManyToOne(targetEntity = Persona.class)
    @JoinColumn(name = "fk_id_persona" , referencedColumnName = "idPersona")
    private Persona persona;
}
