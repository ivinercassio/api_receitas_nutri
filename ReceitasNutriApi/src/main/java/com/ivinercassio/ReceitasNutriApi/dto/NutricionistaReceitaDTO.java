package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entities.Nutricionista;
import com.ivinercassio.ReceitasNutriApi.entities.NutricionistaReceita;
import com.ivinercassio.ReceitasNutriApi.entities.Receita;

public class NutricionistaReceitaDTO {
    
    private Long id;
    private Nutricionista nutricionista;
    private Receita receita;
    private String dataFavoritacao;

    public NutricionistaReceitaDTO() {}

    public NutricionistaReceitaDTO(NutricionistaReceita nutricionistaReceita) {
        this.id = nutricionistaReceita.getId();
        this.nutricionista = nutricionistaReceita.getNutricionista();
        this.receita = nutricionistaReceita.getReceita();
        this.dataFavoritacao = nutricionistaReceita.getDataFavoritacao();
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
