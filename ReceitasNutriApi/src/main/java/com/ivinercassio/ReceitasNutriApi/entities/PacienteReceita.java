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
@Table(name = "tb_paciente_receita")
public class PacienteReceita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne 
    @JoinColumn(name = "paciente_id") // coluna da tabela que referencia o paciente
    private Paciente paciente;

    @ManyToOne 
    @JoinColumn(name = "receita_id") // coluna da tabela que referencia a receita
    private Receita receita;

    @Column(name = "data_favoritacao", nullable = false)
    private String dataFavoritacao;
    // SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    public PacienteReceita() {}

    public PacienteReceita(Paciente paciente, Receita receita, String dataFavoritacao) {
        this.paciente = paciente;
        this.receita = receita;
        this.dataFavoritacao = dataFavoritacao;
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        if (paciente == null) 
            throw new IllegalArgumentException("Paciente não deve ser nulo ou vazio.");
        this.paciente = paciente;
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
