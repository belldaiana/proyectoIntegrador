package com.example.BackPI.repository.impl;

import com.example.BackPI.model.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Integer> {

    @Query("SELECT i FROM  Imagen i WHERE i.producto.id = :idProducto")
    public List<Imagen> findImagenByProductid(@Param("idProducto") Integer idProducto);
}
