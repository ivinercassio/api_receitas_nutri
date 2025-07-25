package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entities.ReceitaIngrediente;

public class ReceitaIngredienteDTO {
    
    private Long id;
    private Long idReceita;
    private Long idIngrediente;
    private String quantidade;

    public ReceitaIngredienteDTO() {}

    public ReceitaIngredienteDTO(ReceitaIngrediente receitaIngrediente) {
        this.id = receitaIngrediente.getId();
        this.idReceita = receitaIngrediente.getReceita().getId();
        this.idIngrediente = receitaIngrediente.getIngrediente().getId();
        this.quantidade = receitaIngrediente.getQuantidade();
    }

    public Long getId() {
        return id;
    }

    public Long getIdIngrediente() {
        return idIngrediente;
    }

    public Long getIdReceita() {
        return idReceita;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdIngrediente(Long idIngrediente) {
        this.idIngrediente = idIngrediente;
    }

    public void setIdReceita(Long idReceita) {
        this.idReceita = idReceita;
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
