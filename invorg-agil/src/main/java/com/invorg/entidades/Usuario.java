package com.invorg.entidades;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    @Column(unique = true, nullable = false,length = 25)
    private  String nombre;
    @Column(nullable = false, length = 25)
    private String clave;
    @ManyToOne(targetEntity = Roles.class)
    @JoinColumn(name = "fk_id_rol",referencedColumnName = "idRol")
    private Roles roles;
    @ManyToOne(targetEntity = Persona.class)
    @JoinColumn(name = "fk_id_persona", referencedColumnName = "idPersona")
    private Persona persona;
}
