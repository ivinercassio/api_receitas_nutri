package com.ivinercassio.ReceitasNutriApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivinercassio.ReceitasNutriApi.entities.Receita;

@Repository
public interface ReceitaIngredienteRepository extends JpaRepository<Receita, Long> {
    List<Receita> findByTituloContainingIgnoreCase(String titulo);
    List<Receita> findByUsuarioNutricionista(Long nutricionistaId);
}
