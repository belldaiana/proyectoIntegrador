package com.example.BackPI.service;

import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.exceptions.ResourceNotFoundException;
import com.example.BackPI.model.Imagen;

import java.util.List;


public interface IImagenService{
    Imagen buscar(Integer id) throws ResourceNotFoundException, BadRequestException;

    List<Imagen> buscar(String titulo);

}
