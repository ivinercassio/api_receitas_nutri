package com.ivinercassio.ReceitasNutriApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ivinercassio.ReceitasNutriApi.dto.ReceitaDTO;
import com.ivinercassio.ReceitasNutriApi.services.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {
    
    @Autowired
    ReceitaService receitaService;

    @GetMapping("")
    public ResponseEntity<List<ReceitaDTO>> findAll() {
        List<ReceitaDTO> list = receitaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceitaDTO> findById(@PathVariable Long id) {
        ReceitaDTO receita = receitaService.findById(id);
        return ResponseEntity.ok().body(receita);
    }

    @PostMapping("") 
    public ResponseEntity<ReceitaDTO> insert(@RequestBody ReceitaDTO receita) {
        ReceitaDTO salvo = receitaService.insert(receita);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaDTO> update(@RequestBody ReceitaDTO receita, @PathVariable Long id) {
        ReceitaDTO alterado = receitaService.update(receita, id);
        return ResponseEntity.ok().body(alterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        receitaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nutricionista/{nutricionistaId}")
    public ResponseEntity<List<ReceitaDTO>> listarPorNutricionista(@PathVariable Long nutricionistaId) {
        
        return ResponseEntity.ok(receitaService.buscarReceitasPorNutricionista(nutricionistaId));
    }
}
