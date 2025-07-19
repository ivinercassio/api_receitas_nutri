package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.ReceitaDTO;
import com.ivinercassio.ReceitasNutriApi.dto.ReceitaDTOSimples;
import com.ivinercassio.ReceitasNutriApi.entities.Nutricionista;
import com.ivinercassio.ReceitasNutriApi.entities.Receita;
import com.ivinercassio.ReceitasNutriApi.repositories.ReceitaRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ReceitaService {
    
    @Autowired
    ReceitaRepository receitaRepository;

    public List<ReceitaDTOSimples> findAll() {
        List<Receita> list = receitaRepository.findAll();
        return list.stream().map(ReceitaDTOSimples::new).toList();
    }

    public ReceitaDTO findById (Long id){
        Receita receita = receitaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Receita não encontrado com ID: " + id));
        return new ReceitaDTO(receita);
    }

    public ReceitaDTO insert(ReceitaDTO receitaDTO) {
        if (receitaRepository.existsByTitulo(receitaDTO.getTitulo()))
            throw new IllegalArgumentException("A Receita já está cadastrado.");

        Nutricionista nutri = new Nutricionista();
        nutri.setId(receitaDTO.getNutricionistaDTO().getId());
        nutri.setNome(receitaDTO.getNutricionistaDTO().getNome());
        nutri.setEmail(receitaDTO.getNutricionistaDTO().getEmail());
        nutri.setEmailContato(receitaDTO.getNutricionistaDTO().getEmailContato());
        nutri.setIntagram(receitaDTO.getNutricionistaDTO().getInstagram());
        nutri.setTelefone(receitaDTO.getNutricionistaDTO().getTelefone());

        Receita nova = new Receita();
        nova.setTitulo(receitaDTO.getTitulo());
        nova.setTempo(receitaDTO.getTempo());
        nova.setRendimento(receitaDTO.getRendimento());
        nova.setNutricionista(nutri);
        nova.setHorario(receitaDTO.getHorario());
        nova.setPreparo(receitaDTO.getPreparo());

        nova = receitaRepository.save(nova);
        return new ReceitaDTO(nova);
    }

    public ReceitaDTO update(ReceitaDTO receitaDTO, Long id) {   
        Receita registro = receitaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Receita não encontrado com ID: " + id));

        Nutricionista nutri = new Nutricionista();
        nutri.setNome(receitaDTO.getNutricionistaDTO().getNome());
        nutri.setEmail(receitaDTO.getNutricionistaDTO().getEmail());
        nutri.setEmailContato(receitaDTO.getNutricionistaDTO().getEmailContato());
        nutri.setIntagram(receitaDTO.getNutricionistaDTO().getInstagram());
        nutri.setTelefone(receitaDTO.getNutricionistaDTO().getTelefone());
        nutri.setId(receitaDTO.getNutricionistaDTO().getId());

        registro.setTitulo(receitaDTO.getTitulo());
        registro.setTempo(receitaDTO.getTempo());
        registro.setRendimento(receitaDTO.getRendimento());
        registro.setNutricionista(nutri);
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

    public List<ReceitaDTOSimples> buscarReceitasPorNutricionista(Long id) {
        List<Receita> list = receitaRepository.findAllByNutricionistaId(id);
        return list.stream().map(ReceitaDTOSimples::new).toList();
    }
}
