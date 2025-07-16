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
@Table(name = "tb_nutricionista_receita")
public class NutricionistaReceita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne 
    @JoinColumn(name = "nutricionista_id") // coluna da tabela que referencia o nutricionista
    private Nutricionista nutricionista;

    @ManyToOne 
    @JoinColumn(name = "receita_id") // coluna da tabela que referencia a receita
    private Receita receita;

    @Column(nullable = false)
    private String dataFavoritacao;
    // SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    public NutricionistaReceita() {}

    public NutricionistaReceita(Nutricionista nutri, Receita receita, String dataFavoritacao) {
        this.nutricionista = nutri;
        this.receita = receita;
        this.dataFavoritacao = dataFavoritacao;
    }

    public Long getId() {
        return id;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista nutricionista) {
        if (nutricionista == null) 
            throw new IllegalArgumentException("Nutricionista não deve ser nulo ou vazio.");
        this.nutricionista = nutricionista;
    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        if (receita == null) 
            throw new IllegalArgumentException("Receita não deve ser nulo ou vazio.");
        this.receita = receita;
    }

    public String getDataFavoritacao() {
        return dataFavoritacao;
    }

    public void setDataFavoritacao(String dataFavoritacao) {
        if (dataFavoritacao == null || dataFavoritacao.isEmpty()) 
            throw new IllegalArgumentException("Data de Favoritação não deve ser nulo ou vazio.");
        this.dataFavoritacao = dataFavoritacao;
    }
    
}
