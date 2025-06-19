package com.ivinercassio.ReceitasNutriApi.services;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.dto.ReceitaIngredienteDTO;
import com.ivinercassio.ReceitasNutriApi.entities.Ingrediente;
import com.ivinercassio.ReceitasNutriApi.entities.Receita;
import com.ivinercassio.ReceitasNutriApi.entities.ReceitaIngrediente;
import com.ivinercassio.ReceitasNutriApi.repositories.ReceitaRepository;
import com.ivinercassio.ReceitasNutriApi.repositories.ReceitaIngredienteRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;;

@Service
@Transactional
public class ReceitaService {
    @Autowired
    ReceitaRepository receitaRepository;
    IngredienteService ingredienteService;
    ReceitaIngredienteRepository receitaIngredienteRepository;

    // Constructor injection...

    public Receita criarReceita(Receita receita, List<ReceitaIngredienteDTO> ingredientesDTO) {
        Receita receitaSalva = receitaRepository.save(receita);
        adicionarIngredientes(receitaSalva, ingredientesDTO);
        return receitaSalva;
    }

    private void adicionarIngredientes(Receita receita, List<ReceitaIngredienteDTO> ingredientesDTO) {
        for (ReceitaIngredienteDTO dto : ingredientesDTO) {
            Ingrediente ingrediente = ingredienteService.buscarPorId(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Ingrediente não encontrado"));
            
            ReceitaIngrediente ri = new ReceitaIngrediente();
            ri.setReceita(receita);
            ri.setQuantidade(dto.getQuantidade());
            
            receitaIngredienteRepository.save(ri);
        }
    }

    public Receita atualizarReceita(Long id, Receita receitaAtualizada, List<ReceitaIngredienteDTO> novosIngredientes) {
        Receita receita = receitaRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Receita não encontrada"));
        
        // Atualiza propriedades básicas
        receita.setTitulo(receitaAtualizada.getTitulo());
        // ... atualizar outros campos
        
        // Atualiza ingredientes
        receitaIngredienteRepository.deleteByReceitaId(id);
        adicionarIngredientes(receita, novosIngredientes);
        
        return receitaRepository.save(receita);
    }

    public List<Receita> buscarReceitasPorNutricionista(Long nutricionistaId) {
        return receitaRepository.findByUsuarioNutricionista(nutricionistaId);
    }
}
