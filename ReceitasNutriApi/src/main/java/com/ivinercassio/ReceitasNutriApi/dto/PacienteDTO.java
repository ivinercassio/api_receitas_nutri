package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entity.Paciente;

public class PacienteDTO {
    private Long id;
    private String nome;
    private String email;

    public PacienteDTO() {}

    public PacienteDTO(Paciente paciente) {
        this.id = paciente.getId();
        this.nome = paciente.getNome();
        this.email = paciente.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }
}
