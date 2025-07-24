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

import com.ivinercassio.ReceitasNutriApi.dto.PacienteReceitaDTO;
import com.ivinercassio.ReceitasNutriApi.services.PacienteReceitaService;

@RestController
@RequestMapping("/pacientes-receitas")
public class PacienteReceitaController {
    
    @Autowired
    PacienteReceitaService pacienteReceitaService;

    @GetMapping("")
    public ResponseEntity<List<PacienteReceitaDTO>> findAll() {
        List<PacienteReceitaDTO> list = pacienteReceitaService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}") // retonro detalhado
    public ResponseEntity<PacienteReceitaDTO> findById(@PathVariable Long id) {
        PacienteReceitaDTO pacienteReceita = pacienteReceitaService.findById(id);
        return ResponseEntity.ok().body(pacienteReceita);
    }

    @PostMapping("") // retorno detalhado
    public ResponseEntity<PacienteReceitaDTO> insert(@RequestBody PacienteReceitaDTO pacienteReceita) {
        PacienteReceitaDTO salvo = pacienteReceitaService.insert(pacienteReceita);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}") // retorno detalhado
    public ResponseEntity<PacienteReceitaDTO> update(@RequestBody PacienteReceitaDTO pacienteReceita, @PathVariable Long id) {
        PacienteReceitaDTO alterado = pacienteReceitaService.update(pacienteReceita, id);
        return ResponseEntity.ok().body(alterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pacienteReceitaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paciente/{pacienteId}") 
    public ResponseEntity<List<PacienteReceitaDTO>> listarPorPaciente(@PathVariable Long pacienteId) {
        List<PacienteReceitaDTO> list = pacienteReceitaService.buscarReceitasPorPaciente(pacienteId);
        return ResponseEntity.ok().body(list);
    }
}
