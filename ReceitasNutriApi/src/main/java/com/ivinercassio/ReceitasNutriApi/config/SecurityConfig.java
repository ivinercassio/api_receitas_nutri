package com.ivinercassio.ReceitasNutriApi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ivinercassio.ReceitasNutriApi.security.JwtAuthenticationFilter;
import com.ivinercassio.ReceitasNutriApi.services.UsuarioDetailsService;

@Configuration
public class SecurityConfig {
    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita verificacao CSRF para permitir POST com token JWT
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Acesso ao H2 Console
                        .requestMatchers("/h2-console/**").permitAll() 
                        // Acesso ao Swagger UI
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() 
                        // Permitir criacao de usuário
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll() 
                        // Permitir endpoint de login
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        // Regras de para Nutricionista
                        .requestMatchers(HttpMethod.GET, "/nutricionistas").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/nutricionistas/{id}").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/nutricionistas").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/nutricionistas/**").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/nutricionistas/**").hasRole("ADMIN")
                        
                        // Regras de para Paciente
                        .requestMatchers(HttpMethod.GET, "/pacientes").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pacientes/{id}").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/pacientes").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pacientes/**").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pacientes/**").hasRole("ADMIN")
                        
                        // Regras de para Ingredientes
                        .requestMatchers(HttpMethod.GET, "/ingredientes").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/ingredientes/{id}").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/ingredientes").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/ingredientes/**").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/ingredientes/**").hasRole("ADMIN")
                        
                        // Regras de para Receitas
                        .requestMatchers(HttpMethod.GET, "/receitas").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/receitas/{id}").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/receitas").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/receitas/**").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/receitas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/receitas/nutricionista/{nutricionistaId}").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        
                        // Regras de para Consumos
                        .requestMatchers(HttpMethod.GET, "/consumos").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/consumos/{id}").hasAnyRole("PACIENTE", "NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/consumos").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/consumos/**").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/consumos/**").hasRole("ADMIN")

                        // Regras de para Paciente Receita
                        .requestMatchers(HttpMethod.GET, "/pacientes-receitas").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pacientes-receitas/{id}").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/pacientes-receitas").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pacientes-receitas/**").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pacientes-receitas/paciente/{pacienteId}").hasAnyRole("NUTRICIONISTA", "PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pacientes-receitas/**").hasRole("ADMIN")

                        // Regras de para Receita Ingrediente
                        .requestMatchers(HttpMethod.GET, "/receitas-ingredientes").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/receitas-ingredientes/{id}").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/receitas-ingredientes").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/receitas-ingredientes/**").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/receitas-ingredientes/**").hasRole("ADMIN")
                        
                        .anyRequest().authenticated() // Todos os outros endpoints exigem autenticação
                )
                .headers(headers -> headers.frameOptions().disable()) // Para H2 Console
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(usuarioDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
