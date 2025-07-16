package com.ivinercassio.ReceitasNutriApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivinercassio.ReceitasNutriApi.entities.Receita;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
    
    public boolean existsByTitulo(String titulo);
    public List<Receita> findAllByNutricionistaId(Long id);
}
