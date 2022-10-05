package com.example.BackPI.service;

import com.example.BackPI.Dto.ProductoDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.model.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService{
    public ProductoDTO guardar(ProductoDTO producto);
    public ProductoDTO editar(ProductoDTO productoDTO, Integer id) throws BadRequestException;
    public ProductoDTO eliminar(Integer id);
    List<Producto> listar();
    Optional<Producto> buscarPorId(Integer id);
    public ProductoDTO buscarPorIdDTO(Integer id);
    public List<ProductoDTO> findByCategoria(Integer categoriaId);
    public List<ProductoDTO> findByCiudad(Integer ciudadId);
    public ProductoDTO findById(Integer id);

    //List<ProductoDTO> findProductoByDateAndCity(String ciudad, LocalDate fechaInicial, LocalDate fechaFinal) throws ResourceNotFoundException;

}
