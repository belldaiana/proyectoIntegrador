package com.example.BackPI.repository;

import com.example.BackPI.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservaRepository  extends JpaRepository<Reserva, Integer> {
}
