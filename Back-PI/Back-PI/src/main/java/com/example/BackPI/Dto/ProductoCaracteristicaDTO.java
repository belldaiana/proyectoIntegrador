package com.example.BackPI.Dto;

import com.example.BackPI.model.Caracteristica;
import com.example.BackPI.model.Producto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCaracteristicaDTO {

    private Integer id;

    private Producto producto;

    private Caracteristica caracteristica;
}
