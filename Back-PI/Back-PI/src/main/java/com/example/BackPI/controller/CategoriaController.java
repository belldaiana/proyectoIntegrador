package com.example.BackPI.controller;

import com.example.BackPI.Dto.CategoriaDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private ICategoriaService categoryService;


    @GetMapping
    public ResponseEntity<Map<String, Object>> index() {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta",categoryService.findAll());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> guardar(@RequestBody CategoriaDTO category){
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta",categoryService.guardar(category));
        return ResponseEntity.created(URI.create("/categoria")).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editar(@RequestBody CategoriaDTO category, @PathVariable Integer id) throws BadRequestException {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta",categoryService.editar(category, id));
        return ResponseEntity.created(URI.create("/categoria")).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", categoryService.buscarPorId(id));
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", categoryService.eliminar(id));
        return ResponseEntity.noContent().build();//(response);
    }
}
