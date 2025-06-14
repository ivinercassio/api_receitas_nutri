package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entity.Consumo;
import com.ivinercassio.ReceitasNutriApi.entity.Paciente;

public class ConsumoDTO {
    private Long id;
    private Paciente paciente;
    private String dataHora;

    public ConsumoDTO() {}

    public ConsumoDTO(Consumo consumo) {
        this.id = consumo.getId();
        this.paciente = consumo.getPaciente();
        this.dataHora = consumo.getDataHora();
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setPaciente(Paciente paciente) {
        if (paciente == null) 
            throw new IllegalArgumentException("Paciente não deve ser nulo ou vazio.");
        this.paciente = paciente;
    }

    public void setDataHora(String dataHora) {
        if (dataHora == null || dataHora.isEmpty()) 
            throw new IllegalArgumentException("Data e Hora não deve ser nulo ou vazio.");
        this.dataHora = dataHora;
    }

    
}
