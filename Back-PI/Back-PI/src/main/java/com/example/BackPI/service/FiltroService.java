package com.example.BackPI.service;

import com.example.BackPI.Dto.ProductoDTO;
import com.example.BackPI.repository.IProductoRepository;
import com.example.BackPI.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class FiltroService implements IFiltroService{

    @Autowired
    private IProductoRepository productoRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public List<ProductoDTO> findProductoPorCiudadYFecha(Integer ciudad, Date fechaInicial, Date fechaFinal) {
        return mapperUtil.mapAll(productoRepository.findProductoPorCiudadYFecha(ciudad, fechaInicial, fechaFinal), ProductoDTO.class);
    }

    @Override
    public List<ProductoDTO> findProductoPorFecha(Date fechaInicial, Date fechaFinal) {
        return mapperUtil.mapAll(productoRepository.findProductoPorFecha(fechaInicial, fechaFinal), ProductoDTO.class);
    }
}
