package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entities.PacienteReceita;
public class PacienteReceitaDTO {
    
    private Long id;
    private Long idPaciente;
    private Long idReceita;
    private String dataFavoritacao;

    public PacienteReceitaDTO() {}

    public PacienteReceitaDTO(PacienteReceita pacienteReceita) {
        this.id = pacienteReceita.getId();
        this.idPaciente = pacienteReceita.getPaciente().getId();
        this.idReceita = pacienteReceita.getReceita().getId();
        this.dataFavoritacao = pacienteReceita.getDataFavoritacao();
    }

    public Long getId() {
        return id;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public Long getIdReceita() {
        return idReceita;
    }
    

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public void setIdReceita(Long idReceita) {
        this.idReceita = idReceita;
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
