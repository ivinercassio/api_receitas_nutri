package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.ReceitaDTO;
import com.ivinercassio.ReceitasNutriApi.entities.Receita;
import com.ivinercassio.ReceitasNutriApi.repositories.ReceitaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ReceitaService {
    
    @Autowired
    ReceitaRepository receitaRepository;

    public List<ReceitaDTO> findAll() {
        List<Receita> list = receitaRepository.findAll();
        return list.stream().map(ReceitaDTO::new).toList();
    }

    public ReceitaDTO findById (Long id){
        Receita receita = receitaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Receita não encontrado com ID: " + id));
        return new ReceitaDTO(receita);
    }

    public ReceitaDTO insert(ReceitaDTO receitaDTO) {
        if (receitaRepository.existsByTitulo(receitaDTO.getTitulo()))
            throw new IllegalArgumentException("A Receita já está cadastrado.");

        Receita nova = new Receita();
        nova.setTitulo(receitaDTO.getTitulo());
        nova.setTempo(receitaDTO.getTempo());
        nova.setRendimento(receitaDTO.getRendimento());
        nova.setNutricionista(receitaDTO.getNutricionista());
        nova.setHorario(receitaDTO.getHorario());

        // addIngredientes(nova, ingredientesDTO); controller proprio
        nova = receitaRepository.save(nova);
        return new ReceitaDTO(nova);
    }

    public ReceitaDTO update(ReceitaDTO receitaDTO, Long id) {   
        Receita registro = receitaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Receita não encontrado com ID: " + id));

        registro.setTitulo(receitaDTO.getTitulo());
        registro.setTempo(receitaDTO.getTempo());
        registro.setRendimento(receitaDTO.getRendimento());
        registro.setNutricionista(receitaDTO.getNutricionista());
        registro.setHorario(receitaDTO.getHorario());

        Receita salva = receitaRepository.save(registro);
        return new ReceitaDTO(salva);
    }

    @Transactional
    public void delete(Long id) {
        if (!receitaRepository.existsById(id))
            throw new IllegalArgumentException("Receita não encontrado com ID: " + id);
        // deletar os dados do receita
        receitaRepository.deleteById(id);;
    }

    public List<ReceitaDTO> buscarReceitasPorNutricionista(Long id) {
        List<Receita> list = receitaRepository.findAllByNutricionistaId(id);
        return list.stream().map(ReceitaDTO::new).toList();
    }
}
