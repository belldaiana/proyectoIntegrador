package com.example.BackPI.repository.impl;

import com.example.BackPI.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

}
