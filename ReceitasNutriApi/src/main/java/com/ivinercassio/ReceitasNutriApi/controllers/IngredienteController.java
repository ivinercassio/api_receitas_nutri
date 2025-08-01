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

import com.ivinercassio.ReceitasNutriApi.dto.IngredienteDTO;
import com.ivinercassio.ReceitasNutriApi.services.IngredienteService;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {
    
    @Autowired
    IngredienteService receitaService;

    @GetMapping("")
    public ResponseEntity<List<IngredienteDTO>> findAll() {
        List<IngredienteDTO> list = receitaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteDTO> findById(@PathVariable Long id) {
        IngredienteDTO receita = receitaService.findById(id);
        return ResponseEntity.ok().body(receita);
    }

    @PostMapping("") 
    public ResponseEntity<IngredienteDTO> insert(@RequestBody IngredienteDTO receita) {
        IngredienteDTO salvo = receitaService.insert(receita);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteDTO> update(@RequestBody IngredienteDTO receita, @PathVariable Long id) {
        IngredienteDTO alterado = receitaService.update(receita, id);
        return ResponseEntity.ok().body(alterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        receitaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
