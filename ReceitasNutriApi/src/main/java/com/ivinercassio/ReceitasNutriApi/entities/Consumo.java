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
@Table(name = "tb_consumo")
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pacienteReceita_id") // coluna da tabela que referencia o paciente receita
    private PacienteReceita pacienteR;

    @Column(name = "datahora_consumo", nullable = false)
    private String dataHora;
    // SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    public Consumo() {}

    public Consumo(PacienteReceita paciente, String dataHora) {
        this.pacienteR = paciente;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public PacienteReceita getPacienteReceita() {
        return this.pacienteR;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public void setPacienteReceita(PacienteReceita paciente) {
        this.pacienteR = paciente;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
