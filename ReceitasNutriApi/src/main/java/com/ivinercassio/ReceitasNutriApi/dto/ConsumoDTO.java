package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entities.Consumo;

public class ConsumoDTO {
    private Long id;
    private Long idPacienteReceita;
    private String dataHora;

    public ConsumoDTO() {}

    public ConsumoDTO(Consumo consumo) {
        this.id = consumo.getId();
        this.idPacienteReceita = consumo.getPacienteReceita().getId();
        this.dataHora = consumo.getDataHora();
    }

    public Long getId() {
        return id;
    }

    public Long getIdPacienteReceita() {
        return idPacienteReceita;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setIdPacienteReceita(Long id) {
        this.idPacienteReceita = id;
    }

    public void setDataHora(String dataHora) {
        if (dataHora == null || dataHora.isEmpty()) 
            throw new IllegalArgumentException("Data e Hora não deve ser nulo ou vazio.");
        this.dataHora = dataHora;
    }

    
}
