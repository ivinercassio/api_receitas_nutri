package com.ivinercassio.ReceitasNutriApi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_receita")
public class Receita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "receita_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "nutricionista_id") // coluna da tabela que referencia a nutricionista
    private Nutricionista nutricionista; 

    @Column(nullable = false, length = 100)
    private String titulo;

    @Column(nullable = false)
    private int rendimento;

    @Column(nullable = false)
    private double tempo;

    @Column(nullable = false)
    private String preparo;

    @Column(nullable = false, length = 20)
    private HorarioEnum horario;

    @Column(nullable = true)
    private String foto;

    public Receita() {}

    public Receita(Nutricionista nutricionista, String titulo, int rendimento, double tempo, String preparo, HorarioEnum horario) {
        this.nutricionista = nutricionista;
        this.titulo = titulo;
        this.rendimento = rendimento;
        this.tempo = tempo;
        this.preparo = preparo;
        this.horario = horario;
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

    public String getPreparo() {
        return preparo;
    }

    public void setPreparo(String preparo) {
        if (preparo == null || preparo.isEmpty()) 
            throw new IllegalArgumentException("Preparo não deve ser nulo ou vazio.");
        this.preparo = preparo;
    }

    public HorarioEnum getHorario() {
        return horario;
    }

    public void setHorario(HorarioEnum horario) {
        this.horario = horario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
}

