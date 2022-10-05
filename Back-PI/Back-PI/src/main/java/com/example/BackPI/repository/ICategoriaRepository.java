package com.example.BackPI.repository;

import com.example.BackPI.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICategoriaRepository extends JpaRepository<Categoria, Integer> {
}
