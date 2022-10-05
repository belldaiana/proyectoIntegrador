package com.example.BackPI.service;

import com.example.BackPI.Dto.ReservaDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.model.Reserva;
import com.example.BackPI.repository.IReservaRepository;
import com.example.BackPI.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService implements IReservaService{

    @Autowired
    private IReservaRepository reservaRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public List<ReservaDTO> findAll() {
        return mapperUtil.mapAll(reservaRepository.findAll(), ReservaDTO.class);
    }

    @Override
    public ReservaDTO findById(Integer id) {
        return mapperUtil.map(reservaRepository.findById(id), ReservaDTO.class);
    }

    @Override
    public ReservaDTO save(ReservaDTO reserva) {
        return mapperUtil.map(reservaRepository.save(mapperUtil.map(reserva, Reserva.class)), ReservaDTO.class);
    }

    @Override
    public ReservaDTO update(ReservaDTO reserva, Integer id) throws BadRequestException {
        Reserva reservaUpdated = reservaRepository.findById(id).orElse(null);
        if (reservaUpdated == null){
            throw new BadRequestException("No se puede editar la reserva con id: " + reserva.getId() +
                    " ya que no se encuentran los datos necesarios para realizar la peticion." );
        }
        Reserva reservaNewData = mapperUtil.map(reserva, Reserva.class);
        reservaUpdated.setFecha_inicial(reservaNewData.getFecha_inicial());
        reservaUpdated.setFecha_final(reservaNewData.getFecha_final());
        reservaUpdated.setUsuario(reservaNewData.getUsuario());
        reservaUpdated.setProducto(reservaNewData.getProducto());
        return mapperUtil.map(reservaRepository.save(reservaUpdated), ReservaDTO.class);
    }

    @Override
    public ReservaDTO delete(Integer id) {
        ReservaDTO reservaDeleted = mapperUtil.map(reservaRepository.findById(id), ReservaDTO.class);
        reservaRepository.delete(mapperUtil.map(reservaDeleted, Reserva.class));
        return reservaDeleted;
    }
}
