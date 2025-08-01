package com.ivinercassio.ReceitasNutriApi.dto;

import com.ivinercassio.ReceitasNutriApi.entities.HorarioEnum;
import com.ivinercassio.ReceitasNutriApi.entities.Receita;

public class ReceitaDTO {
    private Long id;
    private Long idNutricionista; 
    private String titulo;  
    private int rendimento;
    private double tempo;
    private String preparo;
    private HorarioEnum horario;

    public ReceitaDTO() {}

    public ReceitaDTO(Receita receita) {
        this.id = receita.getId();
        this.idNutricionista = receita.getNutricionista().getId();
        this.titulo = receita.getTitulo();
        this.rendimento = receita.getRendimento();
        this.tempo = receita.getTempo();
        this.preparo = receita.getPreparo();
        this.horario = receita.getHorario();
    }

    public Long getId() {
        return id;
    }

    public Long getIdNutricionista() {
        return idNutricionista;
    }

    public void setIdNutricionista(Long id) {
        this.idNutricionista = id;
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

    public String getPreparo() {
        return preparo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPreparo(String preparo) {
        this.preparo = preparo;
    }
}
