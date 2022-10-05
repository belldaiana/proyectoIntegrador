package com.example.BackPI.service;

import com.example.BackPI.Dto.ProductoCaracteristicaDTO;
import com.example.BackPI.exceptions.BadRequestException;

import java.util.List;

public interface IProductoCaracteristicaService {

    public List<ProductoCaracteristicaDTO> findAll();

    public ProductoCaracteristicaDTO findById(Integer id);

    public ProductoCaracteristicaDTO save(ProductoCaracteristicaDTO productoCaracteristica);

    public ProductoCaracteristicaDTO update(ProductoCaracteristicaDTO productoCaracteristica, Integer id) throws BadRequestException;

    public ProductoCaracteristicaDTO delete(Integer id);
}
