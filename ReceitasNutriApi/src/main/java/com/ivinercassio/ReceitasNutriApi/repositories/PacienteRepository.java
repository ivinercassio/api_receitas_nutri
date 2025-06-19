package com.ivinercassio.ReceitasNutriApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ivinercassio.ReceitasNutriApi.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    public boolean existsByEmail(String email);
    
}
