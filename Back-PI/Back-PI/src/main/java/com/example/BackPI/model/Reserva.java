package com.example.BackPI.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reserva")
public class Reserva {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer hora;

    @Column
    private Date fecha_inicial;

    @Column
    private Date fecha_final;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="producto_id")
    private Producto producto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name="usuario_id")
    private Usuario usuario;
}
