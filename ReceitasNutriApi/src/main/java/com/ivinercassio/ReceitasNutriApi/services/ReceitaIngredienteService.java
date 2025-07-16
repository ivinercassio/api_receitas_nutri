package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.ReceitaIngredienteDTO;
import com.ivinercassio.ReceitasNutriApi.entities.Ingrediente;
import com.ivinercassio.ReceitasNutriApi.entities.Receita;
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
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setDescricao(receitaIngredienteDTO.getIngredienteDTO().getDescricao());
        ingrediente.setCalorias(receitaIngredienteDTO.getIngredienteDTO().getCalorias());

        Receita receita = new Receita();
        receita.setTitulo(receitaIngredienteDTO.getReceitaDTO().getTitulo());
        receita.setTempo(receitaIngredienteDTO.getReceitaDTO().getTempo());
        receita.setRendimento(receitaIngredienteDTO.getReceitaDTO().getRendimento());
        receita.setNutricionista(null); // TRATAR ISSO
        receita.setHorario(receitaIngredienteDTO.getReceitaDTO().getHorario());

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

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setDescricao(receitaIngredienteDTO.getIngredienteDTO().getDescricao());
        ingrediente.setCalorias(receitaIngredienteDTO.getIngredienteDTO().getCalorias());

        Receita receita = new Receita();
        receita.setTitulo(receitaIngredienteDTO.getReceitaDTO().getTitulo());
        receita.setTempo(receitaIngredienteDTO.getReceitaDTO().getTempo());
        receita.setRendimento(receitaIngredienteDTO.getReceitaDTO().getRendimento());
        receita.setNutricionista(null); // TRATAR ISSO
        receita.setHorario(receitaIngredienteDTO.getReceitaDTO().getHorario());
        
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

}
