package com.ivinercassio.ReceitasNutriApi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ivinercassio.ReceitasNutriApi.entities.Ingrediente;

@Repository
public interface IngredienteRepository extends JpaRepository<Ingrediente, Long> {
    List<Ingrediente> findByDescricaoContainingIgnoreCase(String descricao);
    boolean existsByDescricao(String descricao);
}
