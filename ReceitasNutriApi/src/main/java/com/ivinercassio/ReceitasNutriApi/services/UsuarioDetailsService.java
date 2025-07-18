package com.ivinercassio.ReceitasNutriApi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.ivinercassio.ReceitasNutriApi.entities.Usuario;
import com.ivinercassio.ReceitasNutriApi.repositories.UsuarioRepository;
import com.ivinercassio.ReceitasNutriApi.security.UsuarioDetails;

public class UsuarioDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));
        return new UsuarioDetails(usuario);
    }
    
}
