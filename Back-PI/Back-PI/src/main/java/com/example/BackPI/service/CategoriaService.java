package com.example.BackPI.service;

import com.example.BackPI.Dto.CategoriaDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.exceptions.ResourceNotFoundException;
import com.example.BackPI.model.Categoria;
import com.example.BackPI.model.Producto;
import com.example.BackPI.repository.impl.CategoriaRepository;
import com.example.BackPI.repository.ICategoriaRepository;
import com.example.BackPI.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService implements ICategoriaService{

    @Autowired
    private ICategoriaRepository categoriaRepository;
    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public List<CategoriaDTO> findAll() {
        return mapperUtil.mapAll(categoriaRepository.findAll(), CategoriaDTO.class);
    }

    @Override
    public CategoriaDTO buscarPorId(Integer id) {
        return mapperUtil.map(categoriaRepository.findById(id), CategoriaDTO.class);
    }

    @Override
    public CategoriaDTO guardar(CategoriaDTO categoriaDTO) {
        return mapperUtil.map(categoriaRepository.save(mapperUtil.map(categoriaDTO, Categoria.class)), CategoriaDTO.class);
    }

    @Override
    public CategoriaDTO editar(CategoriaDTO categoriaDTO, Integer id) throws BadRequestException {
        Categoria categoriaEditada = categoriaRepository.findById(id).orElse(null);
        if (categoriaEditada == null) {
            // error
            throw new BadRequestException("No se puede editar la categoria con id: " + categoriaDTO.getId() +
                    " ya que no se encuentran los datos necesarios para realizar la peticion." );
        }
        categoriaEditada.setTitulo(categoriaDTO.getTitulo());
        categoriaEditada.setDescripcion(categoriaDTO.getDescripcion());
        return mapperUtil.map(categoriaRepository.save(categoriaEditada), CategoriaDTO.class);
    }

    @Override
    public CategoriaDTO eliminar(Integer id) {
        CategoriaDTO categoriaEliminada = mapperUtil.map(categoriaRepository.findById(id), CategoriaDTO.class);
        categoriaRepository.delete(mapperUtil.map(categoriaEliminada, Categoria.class));
        return categoriaEliminada;
    }
}
