package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entities.Consumo;

public class ConsumoDTO {
    private Long id;
    private Long idPaciente;
    private String dataHora;

    public ConsumoDTO() {}

    public ConsumoDTO(Consumo consumo) {
        this.id = consumo.getId();
        this.idPaciente = consumo.getPaciente().getId();
        this.dataHora = consumo.getDataHora();
    }

    public Long getId() {
        return id;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setIdPaciente(Long id) {
        this.idPaciente = id;
    }

    public void setDataHora(String dataHora) {
        if (dataHora == null || dataHora.isEmpty()) 
            throw new IllegalArgumentException("Data e Hora não deve ser nulo ou vazio.");
        this.dataHora = dataHora;
    }

    
}
