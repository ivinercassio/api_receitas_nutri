package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.NutricionistaReceitaDTO;
import com.ivinercassio.ReceitasNutriApi.entities.NutricionistaReceita;
import com.ivinercassio.ReceitasNutriApi.repositories.NutricionistaReceitaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class NutricionistaReceitaService {
    
    @Autowired
    NutricionistaReceitaRepository nutricionistaReceitaRepository;

    public List<NutricionistaReceitaDTO> findAll() {
        List<NutricionistaReceita> list = nutricionistaReceitaRepository.findAll();
        return list.stream().map(NutricionistaReceitaDTO::new).toList();
    }

    public NutricionistaReceitaDTO findById (Long id){
        NutricionistaReceita nutricionistaReceita = nutricionistaReceitaRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("NutricionistaReceita não encontrado com ID: " + id));
        return new NutricionistaReceitaDTO(nutricionistaReceita);
    }

    // relacionamento estabelecido e enviado pelo servico cliente
    public NutricionistaReceitaDTO insert(NutricionistaReceitaDTO nutricionistaReceitaDTO) {
        NutricionistaReceita nova = new NutricionistaReceita();
        nova.setNutricionista(nutricionistaReceitaDTO.getNutricionista());
        nova.setReceita(nutricionistaReceitaDTO.getReceita());
        nova.setDataFavoritacao(nutricionistaReceitaDTO.getDataFavoritacao());

        nova = nutricionistaReceitaRepository.save(nova);
        return new NutricionistaReceitaDTO(nova);
    }

    public NutricionistaReceitaDTO update(NutricionistaReceitaDTO nutricionistaReceitaDTO, Long id) {   
        NutricionistaReceita registro = nutricionistaReceitaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NutricionistaReceita não encontrado com ID: " + id));

        NutricionistaReceita nova = new NutricionistaReceita();
        nova.setNutricionista(nutricionistaReceitaDTO.getNutricionista());
        nova.setReceita(nutricionistaReceitaDTO.getReceita());
        nova.setDataFavoritacao(nutricionistaReceitaDTO.getDataFavoritacao());

        nova = nutricionistaReceitaRepository.save(registro);
        return new NutricionistaReceitaDTO(nova);
    }

    @Transactional
    public void delete(Long id) {
        if (!nutricionistaReceitaRepository.existsById(id))
            throw new IllegalArgumentException("NutricionistaReceita não encontrado com ID: " + id);
        // deletar os dados do nutricionistaReceita
        nutricionistaReceitaRepository.deleteById(id);;
    }

}
