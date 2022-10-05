package com.example.BackPI.service;

import com.example.BackPI.Dto.UsuarioDTO;
import com.example.BackPI.exceptions.BadRequestException;

import java.util.List;

public interface IUsuarioService {

    public UsuarioDTO findByEmail(String email);

    public List<UsuarioDTO> findAll();

    public UsuarioDTO findById(Integer id);

    public UsuarioDTO save(UsuarioDTO usuario);

    public UsuarioDTO update(UsuarioDTO usuario, Integer id) throws BadRequestException;

    public UsuarioDTO delete(Integer id);
}
