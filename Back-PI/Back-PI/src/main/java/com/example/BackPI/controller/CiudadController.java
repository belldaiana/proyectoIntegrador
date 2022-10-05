package com.example.BackPI.controller;

import com.example.BackPI.Dto.CiudadDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.service.ICiudadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ciudad")
public class CiudadController {

    @Autowired
    private ICiudadService ciudadService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> index() {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", ciudadService.listar());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> guardar(@RequestBody CiudadDTO ciudadDTO) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", ciudadService.guardar(ciudadDTO));
        return ResponseEntity.created(URI.create("/ciudad")).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editar(@RequestBody CiudadDTO ciudadDTO, @PathVariable Integer id) throws BadRequestException {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", ciudadService.editar(ciudadDTO, id));
        return ResponseEntity.created(URI.create("/ciudad")).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", ciudadService.buscarPorId(id));
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", ciudadService.eliminar(id));
        return ResponseEntity.noContent().build();//(response);
    }
}

