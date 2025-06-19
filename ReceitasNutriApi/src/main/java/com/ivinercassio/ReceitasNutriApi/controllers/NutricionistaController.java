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

import com.ivinercassio.ReceitasNutriApi.dto.NutricionistaDTO;
import com.ivinercassio.ReceitasNutriApi.services.NutricionistaService;

@RestController
@RequestMapping("/nutricionistas")
public class NutricionistaController {
    
    @Autowired
    NutricionistaService nutricionistaService;

    @GetMapping("")
    public ResponseEntity<List<NutricionistaDTO>> findAll() {
        List<NutricionistaDTO> list = nutricionistaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NutricionistaDTO> findById(@PathVariable Long id) {
        NutricionistaDTO nutricionista = nutricionistaService.findById(id);
        return ResponseEntity.ok().body(nutricionista);
    }

    @PostMapping("") 
    public ResponseEntity<NutricionistaDTO> insert(@RequestBody NutricionistaDTO nutricionista) {
        NutricionistaDTO salvo = nutricionistaService.insert(nutricionista);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NutricionistaDTO> update(@RequestBody NutricionistaDTO nutricionista, @PathVariable Long id) {
        NutricionistaDTO alterado = nutricionistaService.update(nutricionista, id);
        return ResponseEntity.ok().body(alterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        nutricionistaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
