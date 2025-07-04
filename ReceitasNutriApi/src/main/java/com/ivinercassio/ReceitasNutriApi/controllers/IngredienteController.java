package com.ivinercassio.ReceitasNutriApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ivinercassio.ReceitasNutriApi.dto.IngredienteDTO;
import com.ivinercassio.ReceitasNutriApi.services.IngredienteService;

@RestController
@RequestMapping("/ingredientes")
public class IngredienteController {
    
    @Autowired
    private IngredienteService service;

    // findall
    @GetMapping("")
    public ResponseEntity<List<IngredienteDTO>> findAll() {
        List<IngredienteDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    // findById
    @GetMapping("/{id}")
    public ResponseEntity<IngredienteDTO> findById(@PathVariable Long id) {
        IngredienteDTO ingrediente = service.findById(id);
        return ResponseEntity.ok().body(ingrediente);
    }

    // insert
    @PostMapping
    public ResponseEntity<IngredienteDTO> criarIngrediente(@RequestBody IngredienteDTO ingrediente) {
        IngredienteDTO salvo = service.insert(ingrediente);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    // update
    @PutMapping("/{id}")
    public ResponseEntity<IngredienteDTO> update(@RequestBody IngredienteDTO ingrediente, @PathVariable Long id) {
        IngredienteDTO alterado = service.update(ingrediente, id);
        return ResponseEntity.ok().body(alterado);
    }

    // delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // buscar por descrição
    @GetMapping("/{descricao}/buscar")
    public ResponseEntity<List<IngredienteDTO>> buscarPorDescricao(@RequestParam String descricao) {
        List<IngredienteDTO> list = service.buscarPorDescricao(descricao);
        return ResponseEntity.ok().body(list);
    }

}
