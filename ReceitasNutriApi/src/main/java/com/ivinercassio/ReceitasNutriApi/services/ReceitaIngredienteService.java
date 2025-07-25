package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.ReceitaIngredienteDTO;
import com.ivinercassio.ReceitasNutriApi.entities.Ingrediente;
import com.ivinercassio.ReceitasNutriApi.entities.Receita;
import com.ivinercassio.ReceitasNutriApi.entities.ReceitaIngrediente;
import com.ivinercassio.ReceitasNutriApi.repositories.IngredienteRepository;
import com.ivinercassio.ReceitasNutriApi.repositories.ReceitaIngredienteRepository;
import com.ivinercassio.ReceitasNutriApi.repositories.ReceitaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ReceitaIngredienteService {
    
    @Autowired
    ReceitaIngredienteRepository receitaIngredienteRepository;

    @Autowired
    ReceitaRepository receitaRepository;

    @Autowired
    IngredienteRepository ingredienteRepository;

    public List<ReceitaIngredienteDTO> findAll() {
        List<ReceitaIngrediente> list = receitaIngredienteRepository.findAll();
        return list.stream().map(ReceitaIngredienteDTO::new).toList();
    }

    public ReceitaIngredienteDTO findById (Long id){
        ReceitaIngrediente receitaIngrediente = receitaIngredienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ReceitaIngrediente não encontrado com ID: " + id));
        return new ReceitaIngredienteDTO(receitaIngrediente);
    }

    public ReceitaIngredienteDTO insert(ReceitaIngredienteDTO receitaIngredienteDTO) {
        Receita receita = receitaRepository.findById(receitaIngredienteDTO.getIdReceita()).orElseThrow(() -> new EntityNotFoundException("Receita não encontrada com ID: " + receitaIngredienteDTO.getIdReceita()));
    
        Ingrediente ingrediente = ingredienteRepository.findById(receitaIngredienteDTO.getIdIngrediente()).orElseThrow(() -> new EntityNotFoundException("Receita não encontrada com ID: " + receitaIngredienteDTO.getIdIngrediente()));

        ReceitaIngrediente nova = new ReceitaIngrediente();
        nova.setIngrediente(ingrediente);
        nova.setReceita(receita);
        nova.setQuantidade(receitaIngredienteDTO.getQuantidade());

        nova = receitaIngredienteRepository.save(nova);
        return new ReceitaIngredienteDTO(nova);
    }

    public ReceitaIngredienteDTO update(ReceitaIngredienteDTO receitaIngredienteDTO, Long id) {   
        ReceitaIngrediente registro = receitaIngredienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("ReceitaIngrediente não encontrado com ID: " + id));

        Receita receita = receitaRepository.findById(receitaIngredienteDTO.getIdReceita()).orElseThrow(() -> new EntityNotFoundException("Receita não encontrada com ID: " + receitaIngredienteDTO.getIdReceita()));
    
        Ingrediente ingrediente = ingredienteRepository.findById(receitaIngredienteDTO.getIdIngrediente()).orElseThrow(() -> new EntityNotFoundException("Receita não encontrada com ID: " + receitaIngredienteDTO.getIdIngrediente()));
        
        registro.setIngrediente(ingrediente);
        registro.setReceita(receita);
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

    public List<ReceitaIngredienteDTO> findAllByIngredienteDescricao(String descricao) {
        List<ReceitaIngrediente> list = receitaIngredienteRepository.findAllByIngredienteDescricaoContainingIgnoreCase(descricao);
        return list.stream().map(ReceitaIngredienteDTO::new).toList();
    }

    public List<ReceitaIngredienteDTO> findAllByReceitaId(Long id) {
        List<ReceitaIngrediente> list = receitaIngredienteRepository.findAllByReceitaId(id);
        return list.stream().map(ReceitaIngredienteDTO::new).toList();
    }
}
