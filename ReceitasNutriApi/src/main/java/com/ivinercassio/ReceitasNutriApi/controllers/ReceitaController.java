package com.ivinercassio.ReceitasNutriApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ivinercassio.ReceitasNutriApi.dto.ReceitaDTO;
import com.ivinercassio.ReceitasNutriApi.dto.ReceitaIngredienteDTO;
import com.ivinercassio.ReceitasNutriApi.entities.Receita;
import com.ivinercassio.ReceitasNutriApi.services.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {
    @Autowired
    private ReceitaService service;

    @PostMapping
    public ResponseEntity<ReceitaDTO> insert(
        @RequestBody ReceitaDTO receita,
        @RequestParam List<ReceitaIngredienteDTO> ingredientes) {
        
        Receita novaReceita = service.insert(receita, ingredientes);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaReceita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> atualizarReceita(
        @PathVariable Long id,
        @RequestBody ReceitaDTO receita,
        @RequestParam List<ReceitaIngredienteDTO> ingredientes) {
        
        return ResponseEntity.ok(service.atualizarReceita(id, receita, ingredientes));
    }

    @GetMapping("/nutricionista/{nutricionistaId}")
    public ResponseEntity<List<Receita>> listarPorNutricionista(
        @PathVariable Long nutricionistaId) {
        
        return ResponseEntity.ok(service.buscarReceitasPorNutricionista(nutricionistaId));
    }
}
