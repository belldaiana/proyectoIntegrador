package com.example.BackPI.service;

import com.example.BackPI.Dto.ProductoDTO;

import java.util.Date;
import java.util.List;

public interface IFiltroService {

    public List<ProductoDTO> findProductoPorCiudadYFecha(Integer ciudad, Date fechaInicial, Date fechaFinal);

    public List<ProductoDTO> findProductoPorFecha(Date fechaInicial, Date fechaFinal);
}
