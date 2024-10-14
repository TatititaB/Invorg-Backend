package com.invorg.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long idCliente;
    @ManyToOne(targetEntity = Persona.class)
    @JoinColumn(name = "fk_id_persona",referencedColumnName = "idPersona")
    private Persona persona;
}
