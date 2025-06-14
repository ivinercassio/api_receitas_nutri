package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entity.Paciente;
import com.ivinercassio.ReceitasNutriApi.entity.PacienteReceita;
import com.ivinercassio.ReceitasNutriApi.entity.Receita;
public class PacienteReceitaDTO {
    
    private Long id;
    private Paciente paciente;
    private Receita receita;
    private String dataFavoritacao;

    public PacienteReceitaDTO() {}

    public PacienteReceitaDTO(PacienteReceita pacienteReceita) {
        this.id = pacienteReceita.getId();
        this.paciente = pacienteReceita.getPaciente();
        this.receita = pacienteReceita.getReceita();
        this.dataFavoritacao = pacienteReceita.getDataFavoritacao();
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        if (paciente == null) 
            throw new IllegalArgumentException("Paciente não deve ser nulo ou vazio.");
        this.paciente = paciente;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        if (receita == null) 
            throw new IllegalArgumentException("Receita não deve ser nulo ou vazio.");
        this.receita = receita;
    }

    public String getDataFavoritacao() {
        return dataFavoritacao;
    }

    public void setDataFavoritacao(String dataFavoritacao) {
        if (dataFavoritacao == null || dataFavoritacao.isEmpty()) 
            throw new IllegalArgumentException("Data de Favoritação não deve ser nulo ou vazio.");
        this.dataFavoritacao = dataFavoritacao;
    }
    
}
