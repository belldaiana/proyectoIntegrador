package com.example.BackPI.service;

import com.example.BackPI.Dto.UsuarioDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.model.Usuario;
import com.example.BackPI.repository.IUsuarioRepository;
import com.example.BackPI.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public UsuarioDTO findByEmail(String email) {
        return mapperUtil.map(usuarioRepository.findByEmail(email), UsuarioDTO.class);
    }

    @Override
    public List<UsuarioDTO> findAll() {
        return mapperUtil.mapAll(usuarioRepository.findAll(), UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO findById(Integer id) {
        return mapperUtil.map(usuarioRepository.findById(id), UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO save(UsuarioDTO usuario) {
        return mapperUtil.map(usuarioRepository.save(mapperUtil.map(usuario, Usuario.class)), UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO update(UsuarioDTO usuario, Integer id) throws BadRequestException {
        Usuario usuarioUpdated = usuarioRepository.findById(id).orElse(null);
        if (usuarioUpdated == null){
            throw new BadRequestException("No se puede actualizar el rol con id: " + usuario.getId() +
                    " ya que no se encuentran los datos necesarios para realizar la peticion.");
        }
        Usuario usuarioNewData = mapperUtil.map(usuario, Usuario.class);
        usuarioUpdated.setName(usuarioNewData.getName());
        usuarioUpdated.setLastName(usuarioNewData.getLastName());
        usuarioUpdated.setEmail(usuarioNewData.getEmail());
        usuarioUpdated.setCiudad(usuarioNewData.getCiudad());
        usuarioUpdated.setPassword(usuarioNewData.getPassword());
        usuarioUpdated.setRol(usuarioNewData.getRol());
        return mapperUtil.map(usuarioRepository.save(usuarioUpdated), UsuarioDTO.class);
    }

    @Override
    public UsuarioDTO delete(Integer id) {
        UsuarioDTO usuarioDeleted = mapperUtil.map(usuarioRepository.findById(id), UsuarioDTO.class);
        usuarioRepository.delete(mapperUtil.map(usuarioDeleted, Usuario.class));
        return usuarioDeleted;
    }
}
