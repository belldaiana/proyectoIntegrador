package com.example.BackPI.repository;

import com.example.BackPI.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {

    public Usuario findByEmail(String email);
}
