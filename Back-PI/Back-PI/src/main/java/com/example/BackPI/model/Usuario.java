package com.example.BackPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String ciudad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn( name="rol_id")
    private Rol rol;
/*
    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private Set<Reserva> reserva;*/
}
