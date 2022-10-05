package com.example.BackPI.repository.impl;

import com.example.BackPI.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
}
