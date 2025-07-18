package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entities.Consumo;

public class ConsumoDTO {
    private Long id;
    private PacienteDTO paciente;
    private String dataHora;

    public ConsumoDTO() {}

    public ConsumoDTO(Consumo consumo) {
        this.id = consumo.getId();
        this.paciente = new PacienteDTO(consumo.getPaciente());
        this.dataHora = consumo.getDataHora();
    }

    public Long getId() {
        return id;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setPaciente(PacienteDTO paciente) {
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
