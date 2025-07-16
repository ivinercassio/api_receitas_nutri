package com.ivinercassio.ReceitasNutriApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivinercassio.ReceitasNutriApi.entities.PacienteReceita;

public interface PacienteReceitaRepository extends JpaRepository<PacienteReceita, Long> {
    
}
