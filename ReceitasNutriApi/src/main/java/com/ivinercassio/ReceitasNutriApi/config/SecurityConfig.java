package com.ivinercassio.ReceitasNutriApi.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ivinercassio.ReceitasNutriApi.security.JwtAuthenticationFilter;
import com.ivinercassio.ReceitasNutriApi.services.UsuarioDetailsService;

@Configuration
@EnableWebSecurity
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
                .cors() // habilita CORS
                .and()
                // Desabilita verificacao CSRF para permitir POST com token JWT
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Acesso ao H2 Console
                        .requestMatchers("/h2-console/**").permitAll()
                        // Acesso ao Swagger UI
                        .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                        // Permitir criação de usuário
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                        // Permitir endpoint de login
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()

                        // Regras de para Nutricionista
                        .requestMatchers(HttpMethod.GET, "/nutricionistas/email/{email}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/nutricionistas/{id}").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/nutricionistas").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/nutricionistas").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/nutricionistas/**").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/nutricionistas/**").hasRole("ADMIN")

                        // Regras de para Paciente
                        .requestMatchers(HttpMethod.GET, "/pacientes/email/{email}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/pacientes/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/pacientes").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/pacientes").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/pacientes/**").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pacientes/**").hasRole("ADMIN")

                        // Regras de para Ingredientes
                        .requestMatchers(HttpMethod.GET, "/ingredientes/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/ingredientes").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/ingredientes").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/ingredientes/**").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/ingredientes/**").hasRole("ADMIN")

                        // Regras de para Receitas
                        .requestMatchers(HttpMethod.GET, "/receitas/nutricionista/{nutricionistaId}")
                        .hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/receitas/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/receitas").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/receitas").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/receitas/**").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/receitas/**").hasAnyRole("ADMIN", "NUTRICIONISTA")

                        // Regras de para Consumos
                        .requestMatchers(HttpMethod.GET, "/consumos/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/consumos").permitAll()
                        .requestMatchers(HttpMethod.POST, "/consumos").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/consumos/**").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/consumos/**").hasRole("ADMIN")

                        // Regras de para Paciente Receita
                        .requestMatchers(HttpMethod.GET, "/pacientes-receitas/paciente/{pacienteId}")
                        .hasAnyRole("NUTRICIONISTA", "PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pacientes-receitas/{id}").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pacientes-receitas").permitAll()
                        .requestMatchers(HttpMethod.POST, "/pacientes-receitas").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pacientes-receitas/**").hasAnyRole("PACIENTE", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pacientes-receitas/**").hasAnyRole("ADMIN", "PACIENTE")

                        // Regras de para Receita Ingrediente
                        .requestMatchers(HttpMethod.GET, "/receitas-ingredientes/ingrediente/{descricao}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/receitas-ingredientes/receita/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/receitas-ingredientes/{id}")
                        .hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.GET, "/receitas-ingredientes").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/receitas-ingredientes").hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/receitas-ingredientes/**")
                        .hasAnyRole("NUTRICIONISTA", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/receitas-ingredientes/**").hasAnyRole("ADMIN", "NUTRICIONISTA")

                        // Todos os outros endpoints exigem autenticação
                        .anyRequest().authenticated())
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

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:4200"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
