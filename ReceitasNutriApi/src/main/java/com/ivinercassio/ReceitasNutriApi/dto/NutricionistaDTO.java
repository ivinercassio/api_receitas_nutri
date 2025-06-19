package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entities.Nutricionista;

public class NutricionistaDTO {
    private Long id;
    private String nome;
    private String email;
    private String instagram;
    private String emailContato;
    private String telefone;

    public NutricionistaDTO() {}

    public NutricionistaDTO(Nutricionista nutricionista) {
        this.id = nutricionista.getId();
        this.nome = nutricionista.getNome();
        this.email = nutricionista.getEmail();
        this.instagram = nutricionista.getInstagram();
        this.emailContato = nutricionista.getEmailContato();
        this.telefone = nutricionista.getTelefone();
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

    public String getInstagram() {
        return instagram;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }
}
