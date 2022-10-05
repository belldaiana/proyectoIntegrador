package com.example.BackPI.service;

import com.example.BackPI.Dto.CategoriaDTO;
import com.example.BackPI.exceptions.BadRequestException;

import java.util.List;


public interface ICategoriaService{
    public List<CategoriaDTO> findAll();

    public CategoriaDTO buscarPorId(Integer id);

    public CategoriaDTO guardar(CategoriaDTO categoriaDTO);

    public CategoriaDTO editar(CategoriaDTO categoriaDTO, Integer id) throws BadRequestException;

    public CategoriaDTO eliminar(Integer id);
}
