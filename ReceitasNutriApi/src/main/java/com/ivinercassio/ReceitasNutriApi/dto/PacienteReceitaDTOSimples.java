package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entities.PacienteReceita;
public class PacienteReceitaDTOSimples {
    
    private Long id;
    private PacienteDTO paciente;
    private ReceitaDTOSimples receita;
    private String dataFavoritacao;

    public PacienteReceitaDTOSimples() {}

    public PacienteReceitaDTOSimples(PacienteReceita pacienteReceita) {
        this.id = pacienteReceita.getId();
        this.paciente = new PacienteDTO(pacienteReceita.getPaciente());
        this.receita = new ReceitaDTOSimples(pacienteReceita.getReceita());
        this.dataFavoritacao = pacienteReceita.getDataFavoritacao();
    }

    public Long getId() {
        return id;
    }

    public PacienteDTO getPacienteDTO() {
        return paciente;
    }

    public void setPacienteDTO(PacienteDTO paciente) {
        if (paciente == null) 
            throw new IllegalArgumentException("Paciente não deve ser nulo ou vazio.");
        this.paciente = paciente;
    }

    public ReceitaDTOSimples getReceitaDTO() {
        return receita;
    }

    public void setReceitaDTO(ReceitaDTOSimples receita) {
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
