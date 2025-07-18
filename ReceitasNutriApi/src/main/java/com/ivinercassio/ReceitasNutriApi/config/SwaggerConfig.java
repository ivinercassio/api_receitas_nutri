package com.ivinercassio.ReceitasNutriApi.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
                    title = "API - Receitas Nutri", 
                    version = "1.0", 
                    description = "Documentação da API com Swagger"))

@Configuration
public class SwaggerConfig {

}
