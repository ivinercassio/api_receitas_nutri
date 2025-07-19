package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.ReceitaIngredienteDTO;
import com.ivinercassio.ReceitasNutriApi.dto.ReceitaIngredienteDTOSimples;
import com.ivinercassio.ReceitasNutriApi.entities.Ingrediente;
import com.ivinercassio.ReceitasNutriApi.entities.Nutricionista;
import com.ivinercassio.ReceitasNutriApi.entities.Receita;
import com.ivinercassio.ReceitasNutriApi.entities.ReceitaIngrediente;
import com.ivinercassio.ReceitasNutriApi.repositories.ReceitaIngredienteRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class ReceitaIngredienteService {
    
    @Autowired
    ReceitaIngredienteRepository receitaIngredienteRepository;

    public List<ReceitaIngredienteDTOSimples> findAll() {
        List<ReceitaIngrediente> list = receitaIngredienteRepository.findAll();
        return list.stream().map(ReceitaIngredienteDTOSimples::new).toList();
    }

    public ReceitaIngredienteDTO findById (Long id){
        ReceitaIngrediente receitaIngrediente = receitaIngredienteRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ReceitaIngrediente não encontrado com ID: " + id));
        return new ReceitaIngredienteDTO(receitaIngrediente);
    }

    public ReceitaIngredienteDTO insert(ReceitaIngredienteDTO receitaIngredienteDTO) {
        Nutricionista nutri = new Nutricionista();
        nutri.setId(receitaIngredienteDTO.getReceitaDTO().getNutricionistaDTO().getId());
        nutri.setNome(receitaIngredienteDTO.getReceitaDTO().getNutricionistaDTO().getNome());
        nutri.setEmail(receitaIngredienteDTO.getReceitaDTO().getNutricionistaDTO().getEmail());
        nutri.setEmailContato(receitaIngredienteDTO.getReceitaDTO().getNutricionistaDTO().getEmailContato());
        nutri.setIntagram(receitaIngredienteDTO.getReceitaDTO().getNutricionistaDTO().getInstagram());
        nutri.setTelefone(receitaIngredienteDTO.getReceitaDTO().getNutricionistaDTO().getTelefone());
        
        Receita receita = new Receita();
        receita.setId(receitaIngredienteDTO.getReceitaDTO().getId());
        receita.setTitulo(receitaIngredienteDTO.getReceitaDTO().getTitulo());
        receita.setTempo(receitaIngredienteDTO.getReceitaDTO().getTempo());
        receita.setRendimento(receitaIngredienteDTO.getReceitaDTO().getRendimento());
        receita.setNutricionista(nutri);
        receita.setPreparo(receitaIngredienteDTO.getReceitaDTO().getPreparo());
        receita.setHorario(receitaIngredienteDTO.getReceitaDTO().getHorario());
        
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setId(receitaIngredienteDTO.getIngredienteDTO().getId());
        ingrediente.setDescricao(receitaIngredienteDTO.getIngredienteDTO().getDescricao());
        ingrediente.setCalorias(receitaIngredienteDTO.getIngredienteDTO().getCalorias());

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

        Nutricionista nutri = new Nutricionista();
        nutri.setId(receitaIngredienteDTO.getReceitaDTO().getNutricionistaDTO().getId());
        nutri.setNome(receitaIngredienteDTO.getReceitaDTO().getNutricionistaDTO().getNome());
        nutri.setEmail(receitaIngredienteDTO.getReceitaDTO().getNutricionistaDTO().getEmail());
        nutri.setEmailContato(receitaIngredienteDTO.getReceitaDTO().getNutricionistaDTO().getEmailContato());
        nutri.setIntagram(receitaIngredienteDTO.getReceitaDTO().getNutricionistaDTO().getInstagram());
        nutri.setTelefone(receitaIngredienteDTO.getReceitaDTO().getNutricionistaDTO().getTelefone());

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setId(receitaIngredienteDTO.getIngredienteDTO().getId());
        ingrediente.setDescricao(receitaIngredienteDTO.getIngredienteDTO().getDescricao());
        ingrediente.setCalorias(receitaIngredienteDTO.getIngredienteDTO().getCalorias());

        Receita receita = new Receita();
        receita.setId(receitaIngredienteDTO.getReceitaDTO().getId());
        receita.setTitulo(receitaIngredienteDTO.getReceitaDTO().getTitulo());
        receita.setTempo(receitaIngredienteDTO.getReceitaDTO().getTempo());
        receita.setRendimento(receitaIngredienteDTO.getReceitaDTO().getRendimento());
        receita.setNutricionista(nutri);
        receita.setPreparo(receitaIngredienteDTO.getReceitaDTO().getPreparo());
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
