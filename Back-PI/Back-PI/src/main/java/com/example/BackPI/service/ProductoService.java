package com.example.BackPI.service;

import com.example.BackPI.Dto.CaracteristicaDTO;
import com.example.BackPI.Dto.ImagenDTO;
import com.example.BackPI.Dto.ProductoDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.model.*;
import com.example.BackPI.repository.*;
import com.example.BackPI.repository.impl.ImagenRepository;
import com.example.BackPI.util.MapperUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private IProductoCaracteristicaRepository productoCaracteristicaRepository;

    @Autowired
    private ICategoriaRepository categoriaRepository;

    @Autowired
    private ICiudadRepository ciudadRepository;

    @Autowired
    private ImagenRepository imagenRepository;

    @Autowired
    private ICaracteristicaRepository caracteristicaRepository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MapperUtil mapperUtil;


    @Override
    public ProductoDTO guardar(ProductoDTO producto) {
        /**
         * Created product
         * */
        Producto p1 = mapperUtil.map(producto, Producto.class);

        Producto p2 = productoRepository.save(p1);

        /**
         * Obtain object image
         * */
        List<Imagen> listImage = mapperUtil.mapAll(producto.getImagen(), Imagen.class);
        /**
         * Foreach setter to product for the object Image
         * */
        listImage.forEach(i -> i.setProducto(p2));
        imagenRepository.saveAll(listImage);

        /**
         * Features
         * */
        List<ProductoCaracteristica> listProducCarac = new ArrayList<>();
        producto.getCaracteristica().forEach(c -> {
            Caracteristica cc = mapperUtil.map(c, Caracteristica.class);
            ProductoCaracteristica pc = new ProductoCaracteristica(null, p2, cc);
            listProducCarac.add(pc);
        });
        productoCaracteristicaRepository.saveAll(listProducCarac);

        List<ProductoCaracteristica> listProductoSaved = productoCaracteristicaRepository.caracteristicasPorProducto(p2.getId());
        List<Caracteristica> caracteristicaList = new ArrayList<>();
        if (listProductoSaved != null && !listProductoSaved.isEmpty()) {
            listProductoSaved.forEach(cp -> {
                Caracteristica cB = caracteristicaRepository.findById(cp.getCaracteristica().getId()).orElse(null);
                caracteristicaList.add(cB);
            });
        }

        /**
         * Find the last product created
         * */
        Producto productoP = productoRepository.findById(p2.getId()).orElse(null);
        /**
         * consulta a ciudad
         * */
        Categoria cat = categoriaRepository.findById(productoP.getCategoria().getId()).orElse(null);
        /**
         * consulta a categoria
         * */
        Ciudad ciu = ciudadRepository.findById(productoP.getCiudad().getId()).orElse(null);

        productoP.setCiudad(ciu);
        productoP.setCategoria(cat);
        ProductoDTO pdto = mapperUtil.map(productoP, ProductoDTO.class);
        pdto.setImagen(mapperUtil.mapAll(listImage, ImagenDTO.class));
        pdto.setCaracteristica(mapperUtil.mapAll(caracteristicaList, CaracteristicaDTO.class));
        return pdto;
    }

    @Override
    public ProductoDTO editar(ProductoDTO productoDTO, Integer id) throws BadRequestException {
        Producto productoEditado = productoRepository.findById(id).orElse(null);
        if (productoEditado == null) {
            // error
            throw new BadRequestException("No se puede actualizar el producto con id: " + productoDTO.getId() +
                    " ya que no se encuentran los datos necesarios para realizar la peticion.");
        }
        Producto productNewData = mapperUtil.map(productoDTO, Producto.class);
        productoEditado.setCategoria(productNewData.getCategoria());
        productoEditado.setDescripcion(productNewData.getDescripcion());
        productoEditado.setCiudad(productNewData.getCiudad());
        //productoEditado.setImagen(productNewData.getImagen());
        productoEditado.setNombre(productNewData.getNombre());
        /**
         * convertirlo a imagen entidad y ahí si hacer el save de modificación
         */
        List<Imagen> listImagen = mapperUtil.mapAll(productoDTO.getImagen(), Imagen.class);
        listImagen.stream().map(i -> {
            i.setProducto(productoEditado);
            return i;
        });
        imagenRepository.saveAll(listImagen);

        return mapperUtil.map(productoRepository.save(productoEditado), ProductoDTO.class);
    }

    @Override
    public ProductoDTO eliminar(Integer id) {
        ProductoDTO productoEliminado = mapperUtil.map(productoRepository.findById(id), ProductoDTO.class);
        productoRepository.delete(mapperUtil.map(productoEliminado, Producto.class));
        return productoEliminado;
    }

    @Override
    public List<Producto> listar() {
        return mapperUtil.mapAll(productoRepository.findAll(), Producto.class);
    }

    @Override
    public Optional<Producto> buscarPorId(Integer id) {

        return productoRepository.findById(id);
    }

    @Override
    public ProductoDTO buscarPorIdDTO(Integer id) {
        return mapperUtil.map(productoRepository.findById(id), ProductoDTO.class);
    }

    @Override
    public List<ProductoDTO> findByCategoria(Integer categoriaId) {
        List<ProductoDTO> pListDto = mapperUtil.mapAll(productoRepository.findByCategoria(categoriaId), ProductoDTO.class);
        List<ProductoDTO> salida = new ArrayList<>();
        pListDto.forEach(p -> {
            /**buscar imagen y asociarla al producto dto*/
            List<Imagen> lstImagen = imagenRepository.findImagenByProductid(p.getId());
            p.setImagen(mapperUtil.mapAll(lstImagen, ImagenDTO.class));

            salida.add(p);
        });
        return salida;
    }

    @Override
    public List<ProductoDTO> findByCiudad(Integer ciudadId) {
        List<ProductoDTO> pListDto = mapperUtil.mapAll(productoRepository.findByCiudad(ciudadId), ProductoDTO.class);
        List<ProductoDTO> salida = new ArrayList<>();
        pListDto.forEach(p -> {
            /**buscar imagen y asociarla al producto dto*/
            List<Imagen> lstImagen = imagenRepository.findImagenByProductid(p.getId());
            p.setImagen(mapperUtil.mapAll(lstImagen, ImagenDTO.class));

            salida.add(p);
        });
        return salida;
    }

    @Override
    public ProductoDTO findById(Integer id) {
        Producto p = productoRepository.findById(id).orElse(null);
        List<Imagen> lstImagen = imagenRepository.findImagenByProductid(id);
        if(p == null){
            return null;
        }
        ProductoDTO pDto = mapperUtil.map(p, ProductoDTO.class);
        pDto.setImagen(mapperUtil.mapAll(lstImagen, ImagenDTO.class));
        List<CaracteristicaDTO> caDto = mapperUtil.mapAll(productoCaracteristicaRepository.caracteristicasPorProducto(p.getId()), CaracteristicaDTO.class);
        pDto.setCaracteristica(caDto);
        return pDto;
    }
/*
    @Override
    public List<ProductoDTO> findProductoByDateAndCity(String ciudad, LocalDate fechaInicial, LocalDate fechaFinal) throws ResourceNotFoundException {
        List<ProductoDTO> productDtos = new ArrayList<>();
        List<Producto> products = productoRepository.findProductoByDateAndCity(ciudad, fechaInicial, fechaFinal);
        if (!products.isEmpty()){
            for (Producto product : products) {
                ProductoDTO productDto = mapperUtil.map(product, ProductoDTO.class);
                productDtos.add(productDto);
            }
            return productDtos;
        }
        throw new ResourceNotFoundException("La ciudad de: " + ciudad + " no fue encontrada o no existe.");
    }*/
}
