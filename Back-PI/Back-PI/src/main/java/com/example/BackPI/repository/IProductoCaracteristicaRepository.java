package com.example.BackPI.repository;

import com.example.BackPI.model.ProductoCaracteristica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductoCaracteristicaRepository extends JpaRepository<ProductoCaracteristica, Integer> {

    @Query("SELECT c FROM ProductoCaracteristica c WHERE c.producto.id= :idProducto")
    public List<ProductoCaracteristica> caracteristicasPorProducto(@Param("idProducto") Integer idProducto);
}
