package com.example.BackPI.Dto;


import com.example.BackPI.model.Producto;
import com.example.BackPI.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReservaDTO {

    private Integer id;

    private Integer hora;

    private Date fecha_inicial;

    private Date fecha_final;

    private ProductoDTO producto;

    private UsuarioDTO usuario;
}
