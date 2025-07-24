package com.ivinercassio.ReceitasNutriApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivinercassio.ReceitasNutriApi.entities.Nutricionista;

public interface NutricionistaRepository extends JpaRepository <Nutricionista, Long>{
    
    public boolean existsByEmail(String email);
    public Nutricionista findByEmail(String email);
}
