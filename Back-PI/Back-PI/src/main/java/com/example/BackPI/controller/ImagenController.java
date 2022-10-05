package com.example.BackPI.controller;

import com.example.BackPI.exceptions.BadRequestException;
import com.example.BackPI.exceptions.ResourceNotFoundException;
import com.example.BackPI.model.Imagen;
import com.example.BackPI.service.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/imagen")
public class ImagenController {

    @Autowired
    ImagenService imagenService;

    @GetMapping
    public ResponseEntity<List<Imagen>> listarImagenes(){
        return ResponseEntity.ok(imagenService.listarImagen());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imagen> buscarImagen(@PathVariable Integer id){
        Optional<Imagen> imagenBuscada = imagenService.buscarImagenPorId(id);
        return imagenBuscada.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PutMapping
    public ResponseEntity<Imagen> editarImagen(@RequestBody Imagen imagen) throws BadRequestException{
        Imagen imagen1 = imagenService.editarImagen(imagen);
        return ResponseEntity.ok(imagen1);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarImagen(@PathVariable Integer id) throws ResourceNotFoundException{
        imagenService.eliminarImagenPorId(id);
        return ResponseEntity.ok("La imagen con id: " + id + " se ha eliminado correctamente.");
    }
}
