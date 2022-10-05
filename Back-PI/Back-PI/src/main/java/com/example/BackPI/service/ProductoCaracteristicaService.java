package com.example.BackPI.service;

import com.example.BackPI.Dto.ProductoCaracteristicaDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.model.Caracteristica;
import com.example.BackPI.model.Producto;
import com.example.BackPI.model.ProductoCaracteristica;
import com.example.BackPI.repository.ICaracteristicaRepository;
import com.example.BackPI.repository.IProductoCaracteristicaRepository;
import com.example.BackPI.repository.IProductoRepository;
import com.example.BackPI.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoCaracteristicaService implements IProductoCaracteristicaService{

    @Autowired
    private IProductoCaracteristicaRepository productoCaracteristicaRepository;

    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private ICaracteristicaRepository caracteristicaRepository;

    @Autowired
    private MapperUtil mapperUtil;

    @Override
    public List<ProductoCaracteristicaDTO> findAll() {
        return mapperUtil.mapAll(productoCaracteristicaRepository.findAll(), ProductoCaracteristicaDTO.class);
    }

    @Override
    public ProductoCaracteristicaDTO findById(Integer id) {
        return mapperUtil.map(productoCaracteristicaRepository.findById(id), ProductoCaracteristicaDTO.class);
    }

    @Override
    public ProductoCaracteristicaDTO save(ProductoCaracteristicaDTO productoCaracteristica) {
        ProductoCaracteristica p = mapperUtil.map(productoCaracteristica, ProductoCaracteristica.class);
        ProductoCaracteristica pc = productoCaracteristicaRepository.save(p);
        pc.setProducto(productoRepository.findById(pc.getProducto().getId()).orElse(null));
        pc.setCaracteristica(caracteristicaRepository.findById(pc.getCaracteristica().getId()).orElse(null));
        return mapperUtil.map(pc, ProductoCaracteristicaDTO.class);
    }

    @Override
    public ProductoCaracteristicaDTO update(ProductoCaracteristicaDTO productoCaracteristica, Integer id) throws BadRequestException {
        ProductoCaracteristica productoUpdated = productoCaracteristicaRepository.findById(id).orElse(null);
        if (productoUpdated == null){
            throw new BadRequestException("No se puede editar producto-caracteristica con id: " + productoCaracteristica.getId() +
                    " ya que no se encuentran los datos necesarios para realizar la peticion." );
        }
        productoUpdated.setProducto(mapperUtil.map(productoCaracteristica.getProducto(), Producto.class));
        productoUpdated.setCaracteristica(mapperUtil.map(productoCaracteristica.getCaracteristica(), Caracteristica.class));
        return mapperUtil.map(productoCaracteristicaRepository.save(productoUpdated), ProductoCaracteristicaDTO.class);
    }

    @Override
    public ProductoCaracteristicaDTO delete(Integer id) {
        ProductoCaracteristicaDTO productoCaracteristicaDeleted = mapperUtil.map(productoCaracteristicaRepository.findById(id), ProductoCaracteristicaDTO.class);
        productoCaracteristicaRepository.delete(mapperUtil.map(productoCaracteristicaDeleted, ProductoCaracteristica.class));
        return productoCaracteristicaDeleted;
    }
}
