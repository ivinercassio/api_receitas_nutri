package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.PacienteReceitaDTO;
import com.ivinercassio.ReceitasNutriApi.entities.PacienteReceita;
import com.ivinercassio.ReceitasNutriApi.repositories.PacienteReceitaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class PacienteReceitaService {
    
    @Autowired
    PacienteReceitaRepository pacienteReceitaRepository;

    public List<PacienteReceitaDTO> findAll() {
        List<PacienteReceita> list = pacienteReceitaRepository.findAll();
        return list.stream().map(PacienteReceitaDTO::new).toList();
    }

    public PacienteReceitaDTO findById (Long id){
        PacienteReceita pacienteReceita = pacienteReceitaRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("PacienteReceita não encontrado com ID: " + id));
        return new PacienteReceitaDTO(pacienteReceita);
    }

    // relacionamento estabelecido e enviado pelo servico cliente
    public PacienteReceitaDTO insert(PacienteReceitaDTO pacienteReceitaDTO) {
        PacienteReceita nova = new PacienteReceita();
        nova.setPaciente(pacienteReceitaDTO.getPaciente());
        nova.setReceita(pacienteReceitaDTO.getReceita());
        nova.setDataFavoritacao(pacienteReceitaDTO.getDataFavoritacao());

        nova = pacienteReceitaRepository.save(nova);
        return new PacienteReceitaDTO(nova);
    }

    public PacienteReceitaDTO update(PacienteReceitaDTO pacienteReceitaDTO, Long id) {   
        PacienteReceita registro = pacienteReceitaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("PacienteReceita não encontrado com ID: " + id));

        PacienteReceita nova = new PacienteReceita();
        nova.setPaciente(pacienteReceitaDTO.getPaciente());
        nova.setReceita(pacienteReceitaDTO.getReceita());
        nova.setDataFavoritacao(pacienteReceitaDTO.getDataFavoritacao());

        nova = pacienteReceitaRepository.save(registro);
        return new PacienteReceitaDTO(nova);
    }

    @Transactional
    public void delete(Long id) {
        if (!pacienteReceitaRepository.existsById(id))
            throw new IllegalArgumentException("PacienteReceita não encontrado com ID: " + id);
        // deletar os dados do pacienteReceita
        pacienteReceitaRepository.deleteById(id);;
    }

}
