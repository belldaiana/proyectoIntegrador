package com.example.BackPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "ciudad")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ciudad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String ciudad;

    @Column
    private String pais;

   /* @OneToMany(mappedBy = "ciudad", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<Producto> producto;*/

    public Ciudad() {
    }

    public Ciudad(String ciudad, String pais) {
        this.ciudad = ciudad;
        this.pais = pais;
    }

}
