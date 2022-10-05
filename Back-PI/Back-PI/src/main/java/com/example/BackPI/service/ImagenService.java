package com.example.BackPI.service;


import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.exceptions.ResourceNotFoundException;
import com.example.BackPI.model.Imagen;
import com.example.BackPI.repository.impl.ImagenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ImagenService {
    @Autowired
    private ImagenRepository repository;

    @Autowired
    private ProductoService productoService;

    public List<Imagen> listarImagen(){
        return repository.findAll();
    }

    public Optional<Imagen> buscarImagenPorId(Integer id){
        return repository.findById(id);
    }

    public Imagen editarImagen(Imagen imagen) throws BadRequestException{
        Optional<Imagen> imagenBuscada = buscarImagenPorId(imagen.getId());
        if (imagenBuscada.isPresent()){
            return repository.save(imagen);
        } else {
            throw new BadRequestException("No se pudo editar la imagen con id: " + imagen.getId() + " debido a que no se encuentran los elementos necesarios para realizar la petición.");
        }
    }

    public void eliminarImagenPorId(Integer id) throws ResourceNotFoundException {
        Optional<Imagen> imagenBuscada = buscarImagenPorId(id);
        if (imagenBuscada.isPresent()){
            repository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("No se pudo eliminar la imagen con id: " + id + " debido a que no existe.");
        }
    }
}
