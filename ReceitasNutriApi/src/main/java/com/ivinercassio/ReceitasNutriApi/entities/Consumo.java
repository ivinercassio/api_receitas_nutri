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
    @JoinColumn(name = "paciente_id") // coluna da tabela que referencia o paciente
    private Paciente paciente;

    @Column(nullable = false)
    private String dataHora;
    // SimpleDateFormat("dd/MM/yyyy HH:mm:ss")

    public Consumo() {}

    public Consumo(Paciente paciente, String dataHora) {
        this.paciente = paciente;
        this.dataHora = dataHora;
    }

    public Long getId() {
        return id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getDataHora() {
        return dataHora;
    }
}
