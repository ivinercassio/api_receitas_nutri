package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.ivinercassio.ReceitasNutriApi.entities.Usuario;
import com.ivinercassio.ReceitasNutriApi.repositories.UsuarioRepository;

public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // NAO PRECISA SER LOGIN_DTO AO INVES DE USUARIO? 

    public Usuario criarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByLogin(usuario.getLogin())) {
            throw new IllegalArgumentException("Usuário já existe com o login: " + usuario.getLogin());
        }
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));        
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
