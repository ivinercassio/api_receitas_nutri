package com.ivinercassio.ReceitasNutriApi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ivinercassio.ReceitasNutriApi.entities.NivelAcesso;
import com.ivinercassio.ReceitasNutriApi.entities.Usuario;

public class UsuarioDTO {
    private Long id;    
    private String login;
    // Esta anotação impede que a senha seja exposta
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private NivelAcesso nivelAcesso;

    public UsuarioDTO() {}

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.login = usuario.getLogin();
        this.senha = usuario.getSenha();
        this.nivelAcesso = usuario.getNivelAcesso();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public NivelAcesso getNivelAcesso() {
        return nivelAcesso;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setNivelAcesso(NivelAcesso nivelAcesso) {
        this.nivelAcesso = nivelAcesso;
    }

    
}
