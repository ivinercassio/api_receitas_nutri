package com.ivinercassio.ReceitasNutriApi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_ingrediente")
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ingrediente_id")
    private Long id;

    @Column(nullable = false, length = 100)
    private String descricao;

    @Column(nullable = false)
    private Double calorias;

    @Column(nullable = true)
    private String foto;

    public Ingrediente() {}

    public Ingrediente(String descricao, Double calorias, String foto) {
        this.descricao = descricao;
        this.calorias = calorias;
        this.foto = foto;
    }

    public Double getCalorias() {
        return calorias;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getFoto() {
        return foto;
    }

    public Long getId() {
        return id;
    }

    public void setCalorias(Double calorias) {
        this.calorias = calorias;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.isEmpty()) 
            throw new IllegalArgumentException("Descrição não deve ser nula ou vazia.");
        this.descricao = descricao;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
