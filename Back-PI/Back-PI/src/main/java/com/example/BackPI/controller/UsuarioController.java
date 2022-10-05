package com.example.BackPI.controller;

import com.example.BackPI.Dto.UsuarioDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*Método para traer todos los usuarios*/
    @GetMapping
    public ResponseEntity<Map<String, Object>> index() {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", usuarioService.findAll());
        return ResponseEntity.ok(response);
    }

    /*Método para crear un usuario*/
    @PostMapping
    public ResponseEntity<Map<String, Object>> create(@RequestBody UsuarioDTO usuario) {
        Map<String, Object> response = new HashMap<>();
        String passWEncrypt = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passWEncrypt);
        response.put("respuesta", usuarioService.save(usuario));
        return ResponseEntity.created(URI.create("/usuario")).body(response);
    }

    /*Método para editar un usuario*/
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@RequestBody UsuarioDTO usuario, @PathVariable Integer id) throws BadRequestException {
        Map<String, Object> response = new HashMap<>();
        String passWEncrypt = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passWEncrypt);
        response.put("respuesta", usuarioService.update(usuario, id));
        return ResponseEntity.created(URI.create("/usuario")).body(response);
    }

    /*Método para traer un usuario por ID*/
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findById(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", usuarioService.findById(id));
        return ResponseEntity.ok(response);
    }

    /*Método para eliminar un usuario*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", usuarioService.delete(id));
        return ResponseEntity.noContent().build();//(response);
    }
}
