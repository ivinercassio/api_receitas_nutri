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
                // Desabilita verificação CSRF para permitir POST com token JWT
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll() // Acesso ao H2 Console
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll() // Acesso
                                                                                                              // ao
                                                                                                              // Swagger
                                                                                                              // UI
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll() // Permitir criação de usuário
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll() // Permitir endpoint de login
                        .requestMatchers(HttpMethod.GET, "/clientes").hasAnyRole("ADMIN") // Regras de Autorização para
                                                                                          // Clientes
                        .requestMatchers(HttpMethod.GET, "/clientes/{id}").hasAnyRole("GESTOR", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/clientes").hasAnyRole("GESTOR", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/clientes/**").hasAnyRole("GESTOR", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/clientes/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/eventos").hasAnyRole("ADMIN") // Regras de Autorização para
                                                                                         // Eventos
                        .requestMatchers(HttpMethod.GET, "/eventos/{id}").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/eventos").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/eventos/**").hasAnyRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/eventos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/ingressos").hasAnyRole("ADMIN") // Regras de Autorização para
                                                                                           // Ingressos
                        .requestMatchers(HttpMethod.GET, "/ingressos/{id}").hasAnyRole("GESTOR", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/ingressos").hasAnyRole("GESTOR", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/ingressos/**").hasAnyRole("GESTOR", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/ingressos/**").hasRole("ADMIN")
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
