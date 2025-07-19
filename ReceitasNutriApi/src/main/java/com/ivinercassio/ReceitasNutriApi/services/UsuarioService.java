package com.ivinercassio.ReceitasNutriApi.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ivinercassio.ReceitasNutriApi.entities.Usuario;
import com.ivinercassio.ReceitasNutriApi.dto.UsuarioDTO;
import com.ivinercassio.ReceitasNutriApi.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioDTO criarUsuario(UsuarioDTO dto) {
        if (usuarioRepository.existsByLogin(dto.getLogin())) {
            throw new IllegalArgumentException("Usuário já existe com o login: " + dto.getLogin());
        }

        Usuario user = new Usuario();
        user.setLogin(dto.getLogin());
        user.setSenha(passwordEncoder.encode(dto.getSenha()));  
        user.setNivelAcesso(dto.getNivelAcesso());     

        user = usuarioRepository.save(user);
        return new UsuarioDTO(user);
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
}
