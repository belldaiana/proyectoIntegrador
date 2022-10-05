package com.example.BackPI.service;

import com.example.BackPI.Dto.RolDTO;
import com.example.BackPI.exceptions.BadRequestException;

import java.util.List;

public interface IRolService {

    public List<RolDTO> findAll();

    public RolDTO findById(Integer id);

    public RolDTO save(RolDTO rol);

    public RolDTO update(RolDTO rol, Integer id) throws BadRequestException;

    public RolDTO delete(Integer id);
}
