package com.ivinercassio.ReceitasNutriApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivinercassio.ReceitasNutriApi.entities.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{
    
    public boolean existsByDescricao(String descricao);
}
