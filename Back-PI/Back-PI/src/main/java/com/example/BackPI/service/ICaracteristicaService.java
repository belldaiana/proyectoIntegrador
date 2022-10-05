package com.example.BackPI.service;

import com.example.BackPI.Dto.CaracteristicaDTO;
import com.example.BackPI.exceptions.BadRequestException;

import java.util.List;

public interface ICaracteristicaService {
    public List<CaracteristicaDTO> findAll();

    public CaracteristicaDTO buscarPorId(Integer id);

    public CaracteristicaDTO guardar(CaracteristicaDTO caracteristicaDTO);

    public CaracteristicaDTO editar(CaracteristicaDTO caracteristicaDTO, Integer id) throws BadRequestException;

    public CaracteristicaDTO eliminar(Integer id);
}
