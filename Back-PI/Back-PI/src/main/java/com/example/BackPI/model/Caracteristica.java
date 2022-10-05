package com.example.BackPI.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "caracteristica")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Caracteristica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    /*@ManyToMany(mappedBy = "caracteristica",fetch = FetchType.LAZY)
    private Set<Producto> producto = new HashSet<>();*/
}
