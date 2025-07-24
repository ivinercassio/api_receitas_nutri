package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.PacienteReceitaDTO;
import com.ivinercassio.ReceitasNutriApi.entities.Paciente;
import com.ivinercassio.ReceitasNutriApi.entities.PacienteReceita;
import com.ivinercassio.ReceitasNutriApi.entities.Receita;
import com.ivinercassio.ReceitasNutriApi.repositories.PacienteReceitaRepository;
import com.ivinercassio.ReceitasNutriApi.repositories.PacienteRepository;
import com.ivinercassio.ReceitasNutriApi.repositories.ReceitaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PacienteReceitaService {

    @Autowired
    PacienteReceitaRepository pacienteReceitaRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    ReceitaRepository receitaRepository;

    public List<PacienteReceitaDTO> findAll() {
        List<PacienteReceita> list = pacienteReceitaRepository.findAll();
        return list.stream().map(PacienteReceitaDTO::new).toList();
    }

    public PacienteReceitaDTO findById(Long id) {
        PacienteReceita pacienteReceita = pacienteReceitaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PacienteReceita não encontrado com ID: " + id));
        return new PacienteReceitaDTO(pacienteReceita);
    }

    // relacionamento estabelecido e enviado pelo servico cliente
    public PacienteReceitaDTO insert(PacienteReceitaDTO pacienteReceitaDTO) {
        Paciente paciente = pacienteRepository.findById(pacienteReceitaDTO.getIdPaciente())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Paciente não encontrado com ID: " + pacienteReceitaDTO.getIdPaciente()));

        Receita receita = receitaRepository.findById(pacienteReceitaDTO.getIdReceita())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Receita não encontrado com ID: " + pacienteReceitaDTO.getIdReceita()));

        PacienteReceita nova = new PacienteReceita();
        nova.setPaciente(paciente);
        nova.setReceita(receita);
        nova.setDataFavoritacao(pacienteReceitaDTO.getDataFavoritacao());

        nova = pacienteReceitaRepository.save(nova);
        return new PacienteReceitaDTO(nova);
    }

    public PacienteReceitaDTO update(PacienteReceitaDTO pacienteReceitaDTO, Long id) {
        PacienteReceita registro = pacienteReceitaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PacienteReceita não encontrado com ID: " + id));

        Paciente paciente = pacienteRepository.findById(pacienteReceitaDTO.getIdPaciente())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Paciente não encontrado com ID: " + pacienteReceitaDTO.getIdPaciente()));

        Receita receita = receitaRepository.findById(pacienteReceitaDTO.getIdReceita())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Receita não encontrado com ID: " + pacienteReceitaDTO.getIdReceita()));
                        
        registro.setPaciente(paciente);
        registro.setReceita(receita);
        registro.setDataFavoritacao(pacienteReceitaDTO.getDataFavoritacao());

        PacienteReceita nova = pacienteReceitaRepository.save(registro);
        return new PacienteReceitaDTO(nova);
    }

    @Transactional
    public void delete(Long id) {
        if (!pacienteReceitaRepository.existsById(id))
            throw new IllegalArgumentException("PacienteReceita não encontrado com ID: " + id);
        // deletar os dados do pacienteReceita
        pacienteReceitaRepository.deleteById(id);
        ;
    }

    public List<PacienteReceitaDTO> buscarReceitasPorPaciente(Long id) {
        List<PacienteReceita> list = pacienteReceitaRepository.findAllByPacienteId(id);
        return list.stream().map(PacienteReceitaDTO::new).toList();
    }
}
