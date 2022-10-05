package com.example.BackPI.service;

import com.example.BackPI.Dto.CiudadDTO;
import com.example.BackPI.exceptions.BadRequestException;

import java.util.List;

public interface ICiudadService{

    public List<CiudadDTO> listar();

    public CiudadDTO buscarPorId(Integer id);

    public CiudadDTO guardar(CiudadDTO ciudadDTO);

    public CiudadDTO editar(CiudadDTO ciudadDTO, Integer id) throws BadRequestException;

    public CiudadDTO eliminar(Integer id);

}
