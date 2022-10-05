package com.example.BackPI.controller;

import com.example.BackPI.Dto.RolDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.service.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rol")
public class RolController {

    @Autowired
    private IRolService rolService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> index(){
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", rolService.findAll());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody RolDTO rol) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", rolService.save(rol));
        return ResponseEntity.created(URI.create("/rol")).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody RolDTO rol, @PathVariable Integer id) throws BadRequestException {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", rolService.update(rol, id));
        return ResponseEntity.created(URI.create("/rol")).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", rolService.findById(id));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", rolService.delete(id));
        return ResponseEntity.noContent().build();//(response);
    }
}
