package com.ivinercassio.ReceitasNutriApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivinercassio.ReceitasNutriApi.entities.ReceitaIngrediente;

public interface ReceitaIngredienteRepository extends JpaRepository<ReceitaIngrediente, Long> {
    
    public List<ReceitaIngrediente> findAllByIngredienteDescricaoContainingIgnoreCase(String descricao);
    public List<ReceitaIngrediente> findAllByReceitaId(Long id);
}
