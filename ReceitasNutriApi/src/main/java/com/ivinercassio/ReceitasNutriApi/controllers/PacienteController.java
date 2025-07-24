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

import com.ivinercassio.ReceitasNutriApi.dto.PacienteDTO;
import com.ivinercassio.ReceitasNutriApi.services.PacienteService;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    
    @Autowired
    PacienteService pacienteService;

    @GetMapping("")
    public ResponseEntity<List<PacienteDTO>> findAll() {
        List<PacienteDTO> list = pacienteService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> findById(@PathVariable Long id) {
        PacienteDTO paciente = pacienteService.findById(id);
        return ResponseEntity.ok().body(paciente);
    }

    @PostMapping("") 
    public ResponseEntity<PacienteDTO> insert(@RequestBody PacienteDTO paciente) {
        PacienteDTO salvo = pacienteService.insert(paciente);
        return ResponseEntity.status(201).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> update(@RequestBody PacienteDTO paciente, @PathVariable Long id) {
        PacienteDTO alterado = pacienteService.update(paciente, id);
        return ResponseEntity.ok().body(alterado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pacienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<PacienteDTO> findByEmail(@PathVariable String email) {
        PacienteDTO paciente = pacienteService.findByEmail(email);
        return ResponseEntity.ok().body(paciente);
    }
}
