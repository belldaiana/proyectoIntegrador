package com.example.BackPI.Dto;

import com.example.BackPI.model.Caracteristica;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {
    private Integer id;

    private String nombre;

    private String descripcion;

    private CiudadDTO ciudad;

    private CategoriaDTO categoria;

    private List<CaracteristicaDTO> caracteristica;

    private List<ImagenDTO> imagen;

    private boolean disponible;
}