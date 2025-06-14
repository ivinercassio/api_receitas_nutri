package com.ivinercassio.ReceitasNutriApi.dto;

public class PacienteDTO {
    private Long id;
    private String nome;
    private String email;
    private String senha;

    public PacienteDTO() {}

    public PacienteDTO(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public String getSenha() {
        return senha;
    }
}
