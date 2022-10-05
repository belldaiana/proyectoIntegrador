package com.example.BackPI.controller;

import com.example.BackPI.Dto.ProductoCaracteristicaDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.service.IProductoCaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/producto_caracteristica")
public class ProductoCaracteristicaController {

    @Autowired
    private IProductoCaracteristicaService productoCaracteristicaService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> index() {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta",productoCaracteristicaService.findAll());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody ProductoCaracteristicaDTO productoCaracteristica){
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta",productoCaracteristicaService.save(productoCaracteristica));
        return ResponseEntity.created(URI.create("/producto_caracteristica")).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody ProductoCaracteristicaDTO productoCaracteristica, @PathVariable Integer id) throws BadRequestException {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta",productoCaracteristicaService.update(productoCaracteristica, id));
        return ResponseEntity.created(URI.create("/producto_caracteristica")).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", productoCaracteristicaService.findById(id));
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", productoCaracteristicaService.delete(id));
        return ResponseEntity.noContent().build();//(response);
    }
}
