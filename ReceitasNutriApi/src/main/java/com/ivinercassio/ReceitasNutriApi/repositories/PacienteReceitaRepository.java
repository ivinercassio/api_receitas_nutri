package com.ivinercassio.ReceitasNutriApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivinercassio.ReceitasNutriApi.entities.PacienteReceita;
import com.ivinercassio.ReceitasNutriApi.entities.Receita;

public interface PacienteReceitaRepository extends JpaRepository<PacienteReceita, Long> {
    
    public List<Receita> findAllByPacienteId(Long id);
}
