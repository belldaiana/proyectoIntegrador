package com.example.BackPI.controller;

import com.example.BackPI.Dto.CaracteristicaDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.service.ICaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/caracteristica")
public class CaracteristicaController {
    @Autowired
    private ICaracteristicaService caracteristicaService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> index() {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", caracteristicaService.findAll());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> guardar(@RequestBody CaracteristicaDTO caracteristicaDTO) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", caracteristicaService.guardar(caracteristicaDTO));
        return ResponseEntity.created(URI.create("/caracteristica")).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editar(@RequestBody CaracteristicaDTO caracteristicaDTO, @PathVariable Integer id) throws BadRequestException {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", caracteristicaService.editar(caracteristicaDTO, id));
        return ResponseEntity.created(URI.create("/caracteristica")).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", caracteristicaService.buscarPorId(id));
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", caracteristicaService.eliminar(id));
        return ResponseEntity.noContent().build();//(response);
    }
}
