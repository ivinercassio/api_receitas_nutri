package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entities.Ingrediente;
import com.ivinercassio.ReceitasNutriApi.entities.Receita;
import com.ivinercassio.ReceitasNutriApi.entities.ReceitaIngrediente;

public class ReceitaIngredienteDTO {
    
    private Long id;
    private Receita receita;
    private Ingrediente ingrediente;
    private String quantidade;

    public ReceitaIngredienteDTO() {}

    public ReceitaIngredienteDTO(ReceitaIngrediente receitaIngrediente) {
        this.id = receitaIngrediente.getId();
        this.receita = receitaIngrediente.getReceita();
        this.ingrediente = receitaIngrediente.getIngrediente();
        this.quantidade = receitaIngrediente.getQuantidade();
    }

    public Long getId() {
        return id;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setPaciente(Ingrediente ingrediente) {
        if (ingrediente == null) 
            throw new IllegalArgumentException("Ingrediente não deve ser nulo ou vazio.");
        this.ingrediente = ingrediente;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
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
