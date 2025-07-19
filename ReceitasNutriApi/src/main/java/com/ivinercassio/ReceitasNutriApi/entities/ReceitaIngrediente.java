package com.ivinercassio.ReceitasNutriApi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_receita_ingrediente")
public class ReceitaIngrediente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id", nullable = true)
    private Ingrediente ingrediente;

    @Column(nullable = false)
    private String quantidade;

    public ReceitaIngrediente() {}

    public ReceitaIngrediente(Receita receita, Ingrediente ingrediente, String quantidade) {
        this.receita = receita;
        this.ingrediente = ingrediente;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public Ingrediente getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(Ingrediente ingrediente) {
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
            throw new IllegalArgumentException("Quantidade não deve ser nula ou vazio.");
        this.quantidade = quantidade;
    }
}
