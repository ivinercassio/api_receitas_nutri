package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entities.Ingrediente;

public class IngredienteDTO {
    private Long id;
    private String descricao;
    private Double calorias;

    public IngredienteDTO() {}

    public IngredienteDTO(Ingrediente ingrediente) {
        this.id = ingrediente.getId();
        this.descricao = ingrediente.getDescricao();
        this.calorias = ingrediente.getCalorias();
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getCalorias() {
        return calorias;
    }
}
