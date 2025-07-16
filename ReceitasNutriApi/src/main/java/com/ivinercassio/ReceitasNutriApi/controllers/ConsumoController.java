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

import com.ivinercassio.ReceitasNutriApi.dto.ConsumoDTO;
import com.ivinercassio.ReceitasNutriApi.services.ConsumoService;

@RestController
@RequestMapping("/consumos")
public class ConsumoController {
    
    @Autowired
    ConsumoService consumoService;

    @GetMapping("")
    public ResponseEntity<List<ConsumoDTO>> findAll() {
        List<ConsumoDTO> list = consumoService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsumoDTO> findById(@PathVariable Long id) {
        ConsumoDTO consumo = consumoService.findById(id);
        return ResponseEntity.ok().body(consumo);
    }

    @PostMapping("") 
    public ResponseEntity<ConsumoDTO> insert(@RequestBody ConsumoDTO consumo) {
        ConsumoDTO salvo = consumoService.insert(consumo);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsumoDTO> update(@RequestBody ConsumoDTO consumo, @PathVariable Long id) {
        ConsumoDTO alterado = consumoService.update(consumo, id);
        return ResponseEntity.ok().body(alterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consumoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
