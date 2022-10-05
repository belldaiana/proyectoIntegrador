package com.example.BackPI.controller;

import com.example.BackPI.Dto.FiltroDTO;
import com.example.BackPI.service.IFiltroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/filtro")
public class FiltroController {

    @Autowired
    private IFiltroService filtroService;

    /*Método para buscar producto según ciudad e intervalo de fechas*/
    @PostMapping("/ciudad-fechas")
    public ResponseEntity<Map<String, Object>> findProductoPorCiudadYFecha(@RequestBody FiltroDTO filtro) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", filtroService.findProductoPorCiudadYFecha(filtro.getCiudad().getId(), filtro.getFechaInicial(), filtro.getFechaFinal()));
        return ResponseEntity.ok(response);
    }

    /*Método para buscar producto con intervalo de fechas*/
    @PostMapping("/fechas")
    public ResponseEntity<Map<String, Object>> findProductoPorFecha(@RequestBody FiltroDTO filtro) {
        Map<String, Object> response = new HashMap<>();
        response.put("respuesta", filtroService.findProductoPorFecha(filtro.getFechaInicial(), filtro.getFechaFinal()));
        return ResponseEntity.ok(response);
    }
}
