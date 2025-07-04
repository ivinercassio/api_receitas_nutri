package com.ivinercassio.ReceitasNutriApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivinercassio.ReceitasNutriApi.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    void deleteByReceitaId(Long receitaId);
}
