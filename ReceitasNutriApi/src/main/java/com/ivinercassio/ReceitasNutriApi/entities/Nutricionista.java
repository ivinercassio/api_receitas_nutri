package com.ivinercassio.ReceitasNutriApi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_nutricionista")
public class Nutricionista {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nutricionista_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 100)
    private String senha;

    @Column(nullable = true)
    private String foto;

    @Column(nullable = true)
    private String intagram;

    @Column(nullable = true)
    private String emailContato;

    @Column(nullable = true)
    private String telefone;

    public Nutricionista() {}

    public Nutricionista(String nome, String email, String senha, String foto, String intagram, String emailContato, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.foto = foto;
        this.intagram = intagram;
        this.emailContato = emailContato;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getEmailContato() {
        return emailContato;
    }

    public String getFoto() {
        return foto;
    }

    public String getIntagram() {
        return intagram;
    }

    public String getNome() {
        return nome;
    }

    public String getSenha() {
        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Senha não deve ser nulo ou vazio.");
        }
        return senha;
    }

    public String getTelefone() {
        if (telefone == null || telefone.isEmpty()) {
            throw new IllegalArgumentException("Telefone não deve ser nulo ou vazio.");
        }
        return telefone;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Email não deve ser nulo ou vazio.");
        }
        this.email = email;
    }
    
    public void setNome(String nome) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Nome não deve ser nulo ou vazio.");
        }
        this.nome = nome;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Senha não deve ser nulo ou vazio.");
        }
        this.senha = senha;
    }

    public void setEmailContato(String emailContato) {
        this.emailContato = emailContato;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setIntagram(String intagram) {
        this.intagram = intagram;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
