package com.example.BackPI.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private Integer id;

    private String titulo;

    private String descripcion;

    private String urlImagen;
}
