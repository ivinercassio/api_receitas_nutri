package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entities.Nutricionista;

public class NutricionistaDTOSimples {
    private Long id;
    private String nome;

    public NutricionistaDTOSimples() {}

    public NutricionistaDTOSimples(Nutricionista nutricionista) {
        this.id = nutricionista.getId();
        this.nome = nutricionista.getNome();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
