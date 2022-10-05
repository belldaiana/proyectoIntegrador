package com.example.BackPI.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FiltroDTO {

    private CiudadDTO ciudad;

    private Date fechaInicial;

    private Date fechaFinal;
}
