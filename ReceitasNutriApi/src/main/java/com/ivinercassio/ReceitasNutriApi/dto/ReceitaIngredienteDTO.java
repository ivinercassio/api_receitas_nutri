package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entities.ReceitaIngrediente;

public class ReceitaIngredienteDTO {
    
    private Long id;
    private ReceitaDTO receita;
    private IngredienteDTO ingrediente;
    private String quantidade;

    public ReceitaIngredienteDTO() {}

    public ReceitaIngredienteDTO(ReceitaIngrediente receitaIngrediente) {
        this.id = receitaIngrediente.getId();
        this.receita = new ReceitaDTO(receitaIngrediente.getReceita());
        this.ingrediente = new IngredienteDTO(receitaIngrediente.getIngrediente());
        this.quantidade = receitaIngrediente.getQuantidade();
    }

    public Long getId() {
        return id;
    }

    public IngredienteDTO getIngredienteDTO() {
        return ingrediente;
    }

    public void setIngredienteDTO(IngredienteDTO ingrediente) {
        if (ingrediente == null) 
            throw new IllegalArgumentException("Ingrediente não deve ser nulo ou vazio.");
        this.ingrediente = ingrediente;
    }

    public ReceitaDTO getReceitaDTO() {
        return receita;
    }

    public void setReceitaDTO(ReceitaDTO receita) {
        if (receita == null) 
            throw new IllegalArgumentException("Receita não deve ser nulo ou vazio.");
        this.receita = receita;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        if (quantidade == null || quantidade == "") 
            throw new IllegalArgumentException("Quantidade não deve ser nulo ou vazio.");
        this.quantidade = quantidade;
    }
}
