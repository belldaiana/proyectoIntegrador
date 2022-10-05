package com.example.BackPI.repository;

import com.example.BackPI.Dto.ProductoDTO;
import com.example.BackPI.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Integer> {

    public List<Producto> findByCategoria(Integer categoriaId);

    public List<Producto> findByCiudad(Integer ciudadId);
/*
    @Query(value = "SELECT * FROM producto p INNER JOIN ciudad c on p.ciudad_id = c.id WHERE c.ciudad = ?1 AND p.id NOT IN (SELECT fk_producto FROM reserva r WHERE ?2 < r.fecha_final AND ?3 > r.fecha_inicial;);", nativeQuery = true)
    List<Producto> findProductoByDateAndCity(String ciudad, LocalDate fechaInicial, LocalDate fechaFinal);*/

    public List<ProductoDTO> findProductoPorCiudadYFecha(Integer ciudadId, Date fechaInicial, Date fechaFinal);

    public List<Producto> findProductoPorFecha(Date fechaInicial, Date fechaFinal);

}
