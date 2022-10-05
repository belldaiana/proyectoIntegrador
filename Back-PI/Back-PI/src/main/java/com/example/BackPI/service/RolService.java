package com.example.BackPI.service;

import com.example.BackPI.Dto.RolDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.model.Rol;
import com.example.BackPI.repository.IRolRepository;
import com.example.BackPI.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService implements IRolService{

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private MapperUtil mapperUtil;


    @Override
    public List<RolDTO> findAll() {
        return mapperUtil.mapAll(rolRepository.findAll(), RolDTO.class);
    }

    @Override
    public RolDTO findById(Integer id) {
        return mapperUtil.map(rolRepository.findById(id), RolDTO.class);
    }

    @Override
    public RolDTO save(RolDTO rol) {
        return mapperUtil.map(rolRepository.save(mapperUtil.map(rol, Rol.class)), RolDTO.class);
    }

    @Override
    public RolDTO update(RolDTO rol, Integer id) throws BadRequestException {
        Rol rolUpdated = rolRepository.findById(id).orElse(null);
        if (rolUpdated == null){
            //error
            throw new BadRequestException("No se puede actualizar el rol con id: " + rol.getId() +
                    " ya que no se encuentran los datos necesarios para realizar la peticion.");
        }
        rolUpdated.setName(rol.getName());
        rolUpdated.setEnable(rol.getEnable());
        return mapperUtil.map(rolRepository.save(rolUpdated), RolDTO.class);
    }

    @Override
    public RolDTO delete(Integer id) {
        RolDTO rolDeleted = mapperUtil.map(rolRepository.findById(id), RolDTO.class);
        rolRepository.delete(mapperUtil.map(rolDeleted, Rol.class));
        return rolDeleted;
    }
}
