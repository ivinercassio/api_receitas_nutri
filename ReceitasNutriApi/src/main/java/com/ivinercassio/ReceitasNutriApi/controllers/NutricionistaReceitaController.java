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

import com.ivinercassio.ReceitasNutriApi.dto.NutricionistaReceitaDTO;
import com.ivinercassio.ReceitasNutriApi.services.NutricionistaReceitaService;

@RestController
@RequestMapping("/nutricionistas-receitas")
public class NutricionistaReceitaController {
    
    @Autowired
    NutricionistaReceitaService nutricionistaReceitaService;

    @GetMapping("")
    public ResponseEntity<List<NutricionistaReceitaDTO>> findAll() {
        List<NutricionistaReceitaDTO> list = nutricionistaReceitaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NutricionistaReceitaDTO> findById(@PathVariable Long id) {
        NutricionistaReceitaDTO nutricionistaReceita = nutricionistaReceitaService.findById(id);
        return ResponseEntity.ok().body(nutricionistaReceita);
    }

    @PostMapping("") 
    public ResponseEntity<NutricionistaReceitaDTO> insert(@RequestBody NutricionistaReceitaDTO nutricionistaReceita) {
        NutricionistaReceitaDTO salvo = nutricionistaReceitaService.insert(nutricionistaReceita);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NutricionistaReceitaDTO> update(@RequestBody NutricionistaReceitaDTO nutricionistaReceita, @PathVariable Long id) {
        NutricionistaReceitaDTO alterado = nutricionistaReceitaService.update(nutricionistaReceita, id);
        return ResponseEntity.ok().body(alterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nutricionistaReceitaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
