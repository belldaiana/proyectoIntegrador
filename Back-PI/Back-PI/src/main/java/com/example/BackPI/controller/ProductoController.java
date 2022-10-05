package com.example.BackPI.controller;

import com.example.BackPI.Dto.ProductoDTO;
import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.exceptions.ResourceNotFoundException;
import com.example.BackPI.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/producto")
public class ProductoController {
    @Autowired
    private IProductoService productService;

    /*Método para traer todos los productos*/
    @GetMapping
    public ResponseEntity<Map<String, Object>> index() {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", productService.listar());
        return ResponseEntity.ok(response);
    }

    /*Método para crear un producto*/
    @PostMapping
    public ResponseEntity<Map<String, Object>> guardar(@RequestBody ProductoDTO productoDTO) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", productService.guardar(productoDTO));
        return ResponseEntity.created(URI.create("/producto")).body(response);
    }

    /*Método para editar un producto*/
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> editar(@RequestBody ProductoDTO productoDTO, @PathVariable Integer id) throws BadRequestException {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", productService.editar(productoDTO, id));
        return ResponseEntity.ok(response);
    }

    /*Método para traer un producto por ID*/
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> buscarPorId(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", productService.findById(id));
        return ResponseEntity.ok(response);
    }


    /*Método para eliminar un producto*/
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable Integer id) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", productService.eliminar(id));
        return ResponseEntity.noContent().build();//(response);
    }


    /*Método para traer productos según categoria*/
    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<Map<String, Object>> findByCategoria(@PathVariable Integer categoriaId) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", productService.findByCategoria(categoriaId));
        return ResponseEntity.ok(response);
    }

    /*Método para traer productos según ciudad*/
    @GetMapping("/ciudad/{ciudadId}")
    public ResponseEntity<Map<String, Object>> findByCiudad(@PathVariable Integer ciudadId) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", productService.findByCiudad(ciudadId));
        return ResponseEntity.ok(response);
    }
/*
    @GetMapping("/ciudad/{ciudad}/fechaInicial/{fechaInicial}/fechaFinal/{fechaFinal}")
    public ResponseEntity<?> findProductoByDateAndCity(@PathVariable String ciudad, @PathVariable String fechaInicial, @PathVariable String fechaFinal) throws ResourceNotFoundException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        // String a LocalDate
        LocalDate startDate = LocalDate.parse(fechaInicial, formatter);
        LocalDate endDate = LocalDate.parse(fechaFinal, formatter);
        List<ProductoDTO> productDtoList = productService.findProductoByDateAndCity(ciudad, startDate, endDate);
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }*/

}
