package com.example.BackPI.service;

import com.example.BackPI.Dto.CiudadDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.exceptions.ResourceNotFoundException;
import com.example.BackPI.model.Ciudad;
import com.example.BackPI.model.Producto;
import com.example.BackPI.repository.impl.CiudadRepository;
import com.example.BackPI.repository.ICiudadRepository;
import com.example.BackPI.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadService implements ICiudadService {

    @Autowired
    CiudadRepository repository;

    @Autowired
    private ICiudadRepository iCiudadRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public List<CiudadDTO> listar() {
        return mapperUtil.mapAll(iCiudadRepository.findAll(), CiudadDTO.class);
    }

    @Override
    public CiudadDTO buscarPorId(Integer id) {
        return mapperUtil.map(iCiudadRepository.findAllById(id), CiudadDTO.class);
    }

    @Override
    public CiudadDTO guardar(CiudadDTO ciudadDTO) {
        return mapperUtil.map(iCiudadRepository.save(mapperUtil.map(ciudadDTO, Ciudad.class)), CiudadDTO.class);
    }

    @Override
    public CiudadDTO editar(CiudadDTO ciudadDTO, Integer id) throws BadRequestException {
        Ciudad ciudadEditada = iCiudadRepository.findById(id).orElse(null);
        if (ciudadEditada == null) {
            // error
            throw new BadRequestException("No se puede actualizar la ciudad con id: " + ciudadDTO.getId() +
                    " ya que no se encuentran los datos necesarios para realizar la peticion." );
        }
        ciudadEditada.setCiudad(ciudadDTO.getCiudad());
        ciudadEditada.setPais(ciudadDTO.getPais());
        return mapperUtil.map(iCiudadRepository.save(ciudadEditada), CiudadDTO.class);
    }

    @Override
    public CiudadDTO eliminar(Integer id) {
        CiudadDTO ciudadEliminada = mapperUtil.map(iCiudadRepository.findById(id), CiudadDTO.class);
        iCiudadRepository.delete(mapperUtil.map(ciudadEliminada, Ciudad.class));
        return ciudadEliminada;
    }
}
