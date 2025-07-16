package com.ivinercassio.ReceitasNutriApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivinercassio.ReceitasNutriApi.entities.ReceitaIngrediente;

public interface ReceitaIngredienteRepository extends JpaRepository<ReceitaIngrediente, Long> {
    
}
