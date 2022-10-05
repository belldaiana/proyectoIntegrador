package com.example.BackPI.repository;

import com.example.BackPI.model.Categoria;
import com.example.BackPI.model.Ciudad;
import com.example.BackPI.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICiudadRepository extends JpaRepository<Ciudad, Integer> {
    Optional<List<Producto>> findAllById(Integer id);
}
