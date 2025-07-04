package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.IngredienteDTO;
import com.ivinercassio.ReceitasNutriApi.entities.Ingrediente;
import com.ivinercassio.ReceitasNutriApi.repositories.IngredienteRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class IngredienteService {
    
    @Autowired
    IngredienteRepository ingredienteRepository;

    public List<IngredienteDTO> findAll() {
        List<Ingrediente> list = ingredienteRepository.findAll();
        return list.stream().map(IngredienteDTO::new).toList();
    }

    public IngredienteDTO findById (Long id){
        Ingrediente ingrediente = ingredienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado com ID: " + id));
        return new IngredienteDTO(ingrediente);
    }

    public IngredienteDTO insert(IngredienteDTO ingredienteDTO) {
        if (ingredienteRepository.existsByDescricao(ingredienteDTO.getDescricao()))
            throw new IllegalArgumentException("O Ingrediente já está cadastrado.");
        
        Ingrediente novo = new Ingrediente();
        novo.setDescricao(ingredienteDTO.getDescricao());
        novo.setCalorias(ingredienteDTO.getCalorias());
        novo = ingredienteRepository.save(novo);
        return new IngredienteDTO(novo);
    }

    public IngredienteDTO update(IngredienteDTO ingredienteDTO, Long id) {        
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado com ID: " + id));

        // verifica ao alterar descricao
        if (!ingrediente.getDescricao().equals(ingredienteDTO.getDescricao()) && ingredienteRepository.existsByDescricao(ingredienteDTO.getDescricao()))
            throw new IllegalArgumentException("O Ingrediente já está cadastrado.");
        
        ingrediente.setDescricao(ingredienteDTO.getDescricao());
        ingrediente.setCalorias(ingredienteDTO.getCalorias());
        ingrediente = ingredienteRepository.save(ingrediente);
        return new IngredienteDTO(ingrediente);
    }

    @Transactional
    public void delete(Long id) {
        if (!ingredienteRepository.existsById(id))
            throw new IllegalArgumentException("Ingrediente não encontrado com ID: " + id);
        // deletar os dados do ingrediente
        ingredienteRepository.deleteById(id);;
    }

    public List<IngredienteDTO> buscarPorDescricao(String descricao) {
        List<Ingrediente> ingredientes = ingredienteRepository.findByDescricaoContainingIgnoreCase(descricao);
        return ingredientes.stream().map(IngredienteDTO::new).toList();
    }
}
