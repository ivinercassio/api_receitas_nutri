package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entity.HorarioEnum;
import com.ivinercassio.ReceitasNutriApi.entity.Nutricionista;
import com.ivinercassio.ReceitasNutriApi.entity.Receita;

public class ReceitaDTO {
    private Long id;
    private Nutricionista nutricionista; 
    private String titulo;
    private int rendimento;
    private double tempo;
    private HorarioEnum horario;

    public ReceitaDTO() {}

    public ReceitaDTO(Receita receita) {
        this.id = receita.getId();
        this.nutricionista = receita.getNutricionista();
        this.titulo = receita.getTitulo();
        this.rendimento = receita.getRendimento();
        this.tempo = receita.getTempo();
        this.horario = receita.getHorario();
    }

    public Long getId() {
        return id;
    }

    public Nutricionista getNutricionista() {
        return nutricionista;
    }

    public void setNutricionista(Nutricionista nutricionista) {
        if (nutricionista == null)
            throw new IllegalArgumentException("Nutricionista não deve ser nula ou vazia");
        this.nutricionista = nutricionista;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) 
            throw new IllegalArgumentException("Título não deve ser nulo ou vazio.");
        this.titulo = titulo;
    }

    public int getRendimento() {
        return rendimento;
    }

    public void setRendimento(int rendimento) {
        if (rendimento <= 0) 
            throw new IllegalArgumentException("Rendimento deve ser maior que zero.");
        this.rendimento = rendimento;
    }

    public double getTempo() {
        return tempo;
    }

    public void setTempo(double tempo) {
        if (tempo <= 0) 
            throw new IllegalArgumentException("Tempo deve ser maior que zero.");
        this.tempo = tempo;
    }

    public HorarioEnum getHorario() {
        return horario;
    }

    public void setHorario(HorarioEnum horario) {
        this.horario = horario;
    }
}
