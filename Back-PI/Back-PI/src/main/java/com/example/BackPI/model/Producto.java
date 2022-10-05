package com.example.BackPI.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.awt.*;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "producto")
@NamedQueries({
        @NamedQuery(name = "Producto.findByCiudad", query = "SELECT p FROM Producto p WHERE p.ciudad.id = :ciudadId"),
        @NamedQuery(name = "Producto.findByCategoria", query = "SELECT p FROM Producto p WHERE p.categoria.id = :categoriaId"),
        @NamedQuery(name = "Producto.findProductoPorCiudadYFecha", query = "SELECT p FROM Producto p " +
                "WHERE NOT EXISTS(" +
                "SELECT 1 FROM Reserva r WHERE r.producto.id = p.id " +
                "AND :fecha_final <= r.fecha_final AND :fecha_inicial <= r.fecha_inicial"  +
                ") AND p.ciudad.id = :citudadId"),
        @NamedQuery(name = "Producto.findProductoPorFecha", query = "SELECT p FROM Producto p " +
                "WHERE NOT EXISTS(" +
                "SELECT 1 FROM Reserva r WHERE r.producto.id = p.id " +
                "AND :fecha_final <= r.fecha_final AND :fecha_inicial <= r.fecha_inicial"  +
                ")")
})
public class Producto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private double precio;

    @Column
    private boolean disponible;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="categoria_id")
    private Categoria categoria;

   /* @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "producto_caracteristica", joinColumns = @JoinColumn(name = "producto_id"), inverseJoinColumns = @JoinColumn(name = "caracteristica_id"), uniqueConstraints = {
            @UniqueConstraint(columnNames = { "producto_id", "caracteristica_id" }) })
    private List<Caracteristica> caracteristica;*/

/*
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id")
    private List<Imagen> imagen;*/

    /*@OneToMany(cascade = {CascadeType.ALL})
    @JoinColumn(name = "producto_id")
    private List<Imagen> imagen;*/

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="ciudad_id")
    private Ciudad ciudad;

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", disponible=" + disponible +
                ", categoria=" + categoria +/*
                ", caracteristica=" + caracteristica +*/
                //", imagen=" + imagen +
                ", ciudad=" + ciudad +
                '}';
    }

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private Set<Reserva> reserva;
}
