package com.example.BackPI.controller;

import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface CRUDController<T>{
    ResponseEntity<?> crear(@RequestBody T t) throws BadRequestException, ResourceNotFoundException;

    ResponseEntity<?> buscarPorId(@PathVariable Integer id) throws BadRequestException, ResourceNotFoundException;

    ResponseEntity<?> editar(@RequestBody T t) throws BadRequestException, ResourceNotFoundException;

    ResponseEntity<String> eliminar(@PathVariable Integer id) throws BadRequestException, ResourceNotFoundException;

    ResponseEntity<?> buscarTodos();
}
