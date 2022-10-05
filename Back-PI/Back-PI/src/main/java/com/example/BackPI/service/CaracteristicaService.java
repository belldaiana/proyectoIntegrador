package com.example.BackPI.service;

import com.example.BackPI.Dto.CaracteristicaDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.exceptions.ResourceNotFoundException;
import com.example.BackPI.model.Caracteristica;
import com.example.BackPI.repository.ICaracteristicaRepository;
import com.example.BackPI.repository.impl.CaracteristicaRepository;
import com.example.BackPI.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaracteristicaService implements ICaracteristicaService{

    @Autowired
    private ICaracteristicaRepository repository;

    @Autowired
    private MapperUtil mapperUtil;


    @Override
    public List<CaracteristicaDTO> findAll() {
        return mapperUtil.mapAll(repository.findAll(), CaracteristicaDTO.class);
    }

    @Override
    public CaracteristicaDTO buscarPorId(Integer id) {
        return mapperUtil.map(repository.findById(id), CaracteristicaDTO.class);
    }

    @Override
    public CaracteristicaDTO guardar(CaracteristicaDTO caracteristicaDTO) {
        return mapperUtil.map(repository.save(mapperUtil.map(caracteristicaDTO, Caracteristica.class)), CaracteristicaDTO.class);
    }

    @Override
    public CaracteristicaDTO editar(CaracteristicaDTO caracteristicaDTO, Integer id) throws BadRequestException {
        Caracteristica caracteristicaEditada = repository.findById(id).orElse(null);
        if (caracteristicaEditada == null) {
            // error
            throw new BadRequestException("No se puede actualizar la caracteristica con id: " + caracteristicaDTO.getId() +
                    " ya que no se encuentran los datos necesarios para realizar la peticion." );
        }
        caracteristicaEditada.setNombre(caracteristicaDTO.getNombre());
        return mapperUtil.map(repository.save(caracteristicaEditada), CaracteristicaDTO.class);
    }

    @Override
    public CaracteristicaDTO eliminar(Integer id) {
        CaracteristicaDTO caracteristicaEliminada = mapperUtil.map(repository.findById(id), CaracteristicaDTO.class);
        repository.delete(mapperUtil.map(caracteristicaEliminada, Caracteristica.class));
        return caracteristicaEliminada;
    }
}
