package com.ivinercassio.ReceitasNutriApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivinercassio.ReceitasNutriApi.entities.Consumo;

public interface ConsumoRepository extends JpaRepository<Consumo, Long>{
    
}
