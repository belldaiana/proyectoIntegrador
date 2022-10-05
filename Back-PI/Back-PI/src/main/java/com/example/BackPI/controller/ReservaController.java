package com.example.BackPI.controller;

import com.example.BackPI.Dto.ReservaDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.service.IReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/reserva")
public class ReservaController {

    @Autowired
    private IReservaService reservaService;

    @GetMapping
    public ResponseEntity<Map<String, Object>> index() {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", reservaService.findAll());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody ReservaDTO reserva) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", reservaService.save(reserva));
        return ResponseEntity.created(URI.create("/reserva")).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody ReservaDTO reserva, @PathVariable Integer id) throws BadRequestException {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", reservaService.update(reserva, id));
        return ResponseEntity.created(URI.create("/reserva")).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", reservaService.findById(id));
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", reservaService.delete(id));
        return ResponseEntity.noContent().build();//(response);
    }
}
