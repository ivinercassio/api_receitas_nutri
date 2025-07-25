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

import com.ivinercassio.ReceitasNutriApi.dto.ReceitaIngredienteDTO;
import com.ivinercassio.ReceitasNutriApi.services.ReceitaIngredienteService;

@RestController
@RequestMapping("/receitas-ingredientes")
public class ReceitaIngredienteController {
    
    @Autowired
    ReceitaIngredienteService receitaIngredienteService;

    @GetMapping("")
    public ResponseEntity<List<ReceitaIngredienteDTO>> findAll() {
        List<ReceitaIngredienteDTO> list = receitaIngredienteService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}") // retorno detalhado
    public ResponseEntity<ReceitaIngredienteDTO> findById(@PathVariable Long id) {
        ReceitaIngredienteDTO receita = receitaIngredienteService.findById(id);
        return ResponseEntity.ok().body(receita);
    }

    @PostMapping("") // retorno detalhado
    public ResponseEntity<ReceitaIngredienteDTO> insert(@RequestBody ReceitaIngredienteDTO receita) {
        ReceitaIngredienteDTO salvo = receitaIngredienteService.insert(receita);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}") // retorno detalhado
    public ResponseEntity<ReceitaIngredienteDTO> update(@RequestBody ReceitaIngredienteDTO receita, @PathVariable Long id) {
        ReceitaIngredienteDTO alterado = receitaIngredienteService.update(receita, id);
        return ResponseEntity.ok().body(alterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        receitaIngredienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/ingrediente/{descricao}")
    public ResponseEntity<List<ReceitaIngredienteDTO>> findAllByIngredienteDescricao(@PathVariable String descricao) {
        List<ReceitaIngredienteDTO> list = receitaIngredienteService.findAllByIngredienteDescricao(descricao);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/receita/{id}")
    public ResponseEntity<List<ReceitaIngredienteDTO>> findAllByReceitaId(@PathVariable Long id) {
        List<ReceitaIngredienteDTO> list = receitaIngredienteService.findAllByReceitaId(id);
        return ResponseEntity.ok().body(list);
    }
}
