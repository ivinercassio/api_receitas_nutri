package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.ReceitaIngredienteDTO;
import com.ivinercassio.ReceitasNutriApi.entities.ReceitaIngrediente;
import com.ivinercassio.ReceitasNutriApi.repositories.ReceitaIngredienteRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ReceitaIngredienteService {
    
    @Autowired
    ReceitaIngredienteRepository receitaIngredienteRepository;

    public List<ReceitaIngredienteDTO> findAll() {
        List<ReceitaIngrediente> list = receitaIngredienteRepository.findAll();
        return list.stream().map(ReceitaIngredienteDTO::new).toList();
    }

    public ReceitaIngredienteDTO findById (Long id){
        ReceitaIngrediente receitaIngrediente = receitaIngredienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ReceitaIngrediente não encontrado com ID: " + id));
        return new ReceitaIngredienteDTO(receitaIngrediente);
    }

    public ReceitaIngredienteDTO insert(ReceitaIngredienteDTO receitaIngredienteDTO) {
        ReceitaIngrediente nova = new ReceitaIngrediente();
        nova.setIngrediente(receitaIngredienteDTO.getIngrediente());
        nova.setReceita(receitaIngredienteDTO.getReceita());
        nova.setQuantidade(receitaIngredienteDTO.getQuantidade());

        nova = receitaIngredienteRepository.save(nova);
        return new ReceitaIngredienteDTO(nova);
    }

    public ReceitaIngredienteDTO update(ReceitaIngredienteDTO receitaIngredienteDTO, Long id) {   
        ReceitaIngrediente registro = receitaIngredienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ReceitaIngrediente não encontrado com ID: " + id));

        registro.setIngrediente(receitaIngredienteDTO.getIngrediente());
        registro.setReceita(receitaIngredienteDTO.getReceita());
        registro.setQuantidade(receitaIngredienteDTO.getQuantidade());

        registro = receitaIngredienteRepository.save(registro);
        return new ReceitaIngredienteDTO(registro);
    }

    @Transactional
    public void delete(Long id) {
        if (!receitaIngredienteRepository.existsById(id))
            throw new IllegalArgumentException("ReceitaIngrediente não encontrado com ID: " + id);
        // deletar os dados do receita
        receitaIngredienteRepository.deleteById(id);;
    }

}
