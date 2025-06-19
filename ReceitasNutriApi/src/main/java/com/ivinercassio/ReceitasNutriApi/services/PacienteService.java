package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.PacienteDTO;
import com.ivinercassio.ReceitasNutriApi.entities.Paciente;
import com.ivinercassio.ReceitasNutriApi.repositories.PacienteRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PacienteService {
    
    @Autowired
    PacienteRepository pacienteRepository;

    public List<PacienteDTO> findAll() {
        List<Paciente> list = pacienteRepository.findAll();
        return list.stream().map(PacienteDTO::new).toList();
    }

    public PacienteDTO findById (Long id){
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com ID: " + id));
        return new PacienteDTO(paciente);
    }

    public PacienteDTO insert(PacienteDTO pacienteDTO) {
        if (pacienteRepository.existsByEmail(pacienteDTO.getEmail()))
            throw new IllegalArgumentException("O E-mail já está cadastrado.");
        
        Paciente novo = new Paciente();
        novo.setNome(pacienteDTO.getNome());
        novo.setEmail(pacienteDTO.getEmail());
        // salvo sem senha
        novo.setSenha("sem senha");
        novo = pacienteRepository.save(novo);
        return new PacienteDTO(novo);
    }

    public PacienteDTO update(PacienteDTO pacienteDTO, Long id) {        
        Paciente paciente = pacienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com ID: " + id));

        // verifica ao alterar o email
        if (!paciente.getEmail().equals(pacienteDTO.getEmail()) && pacienteRepository.existsByEmail(pacienteDTO.getEmail()))
            throw new IllegalArgumentException("O E-mail já está cadastrado.");
        
        paciente.setEmail(pacienteDTO.getEmail());
        paciente.setNome(pacienteDTO.getNome());
        paciente = pacienteRepository.save(paciente);
        return new PacienteDTO(paciente);
    }

    @Transactional
    public void delete(Long id) {
        if (!pacienteRepository.existsById(id))
            throw new IllegalArgumentException("Paciente não encontrado com ID: " + id);
        // deletar os dados do paciente
        pacienteRepository.deleteById(id);;
    }
}
