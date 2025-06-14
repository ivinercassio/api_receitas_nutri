package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entity.Nutricionista;

public class NutricionistaDTO {
    private Long id;
    private String nome;
    private String email;
    private String intagram;
    private String emailContato;
    private String telefone;

    public NutricionistaDTO() {}

    public NutricionistaDTO(Nutricionista nutricionista) {
        this.id = nutricionista.getId();
        this.nome = nutricionista.getNome();
        this.email = nutricionista.getEmail();
        this.intagram = nutricionista.getIntagram();
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

    public String getIntagram() {
        return intagram;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }
}
