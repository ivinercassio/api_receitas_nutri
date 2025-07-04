package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.NutricionistaDTO;
import com.ivinercassio.ReceitasNutriApi.entities.Nutricionista;
import com.ivinercassio.ReceitasNutriApi.repositories.NutricionistaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class NutricionistaService {

    @Autowired
    NutricionistaRepository nutricionistaRepository;

    public List<NutricionistaDTO> findAll() {
        List<Nutricionista> list = nutricionistaRepository.findAll();
        return list.stream().map(NutricionistaDTO::new).toList();
    }

    public NutricionistaDTO findById (Long id){
        Nutricionista nutricionista = nutricionistaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nutricionista não encontrado com ID: " + id));
        return new NutricionistaDTO(nutricionista);
    }

    public NutricionistaDTO insert(NutricionistaDTO nutricionistaDTO) {
        if (nutricionistaRepository.existsByEmail(nutricionistaDTO.getEmail()))
            throw new IllegalArgumentException("O E-mail já está cadastrado.");
        
        Nutricionista novo = new Nutricionista();
        novo.setNome(nutricionistaDTO.getNome());
        novo.setEmail(nutricionistaDTO.getEmail());
        novo.setIntagram(nutricionistaDTO.getInstagram());
        novo.setEmailContato(nutricionistaDTO.getEmailContato());
        novo.setTelefone(nutricionistaDTO.getTelefone());
        // salvo sem senha
        novo.setSenha("sem senha");
        novo = nutricionistaRepository.save(novo);
        return new NutricionistaDTO(novo);
    }

    public NutricionistaDTO update(NutricionistaDTO nutricionistaDTO, Long id) {        
        Nutricionista nutricionista = nutricionistaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Nutricionista não encontrado com ID: " + id));

        // verifica ao alterar o email
        if (!nutricionista.getEmail().equals(nutricionistaDTO.getEmail()) && nutricionistaRepository.existsByEmail(nutricionistaDTO.getEmail()))
            throw new IllegalArgumentException("O E-mail já está cadastrado.");
        
        nutricionista.setEmail(nutricionistaDTO.getEmail());
        nutricionista.setNome(nutricionistaDTO.getNome());
        nutricionista = nutricionistaRepository.save(nutricionista);
        return new NutricionistaDTO(nutricionista);
    }

    @Transactional
    public void delete(Long id) {
        if (!nutricionistaRepository.existsById(id))
            throw new IllegalArgumentException("Nutricionista não encontrado com ID: " + id);
        // deletar os dados do nutricionista
        nutricionistaRepository.deleteById(id);;
    }
}
