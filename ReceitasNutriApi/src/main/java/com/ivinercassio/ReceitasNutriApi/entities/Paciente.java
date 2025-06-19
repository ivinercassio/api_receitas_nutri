package com.ivinercassio.ReceitasNutriApi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_paciente")
public class Paciente {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paciente_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    public Paciente() {}

    public Paciente(String nome, String email, String senha) {
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

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) 
            throw new IllegalArgumentException("Email não deve ser nulo ou vazio.");
        this.email = email;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) 
            throw new IllegalArgumentException("Nome não deve ser nulo ou vazio.");
        this.nome = nome;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.isEmpty()) 
            throw new IllegalArgumentException("Senha não deve ser nulo ou vazio.");
        this.senha = senha;
    }
    
}
