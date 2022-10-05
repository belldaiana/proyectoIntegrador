package com.example.BackPI.service;

import com.example.BackPI.Dto.ReservaDTO;
import com.example.BackPI.exceptions.BadRequestException;

import java.util.List;

public interface IReservaService{
    public List<ReservaDTO> findAll();

    public ReservaDTO findById(Integer id);

    public ReservaDTO save(ReservaDTO reserva);

    public ReservaDTO update(ReservaDTO reserva, Integer id) throws BadRequestException;

    public ReservaDTO delete(Integer id);
}
