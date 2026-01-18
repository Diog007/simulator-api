package com.simulador.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Configuração restrita para as APIs administrativas (Traffic e Mocks)
                // Permitindo apenas a origem do frontend hospedado
                registry.addMapping("/api/admin/**")
                        .allowedOrigins(
                            "https://mockflow.diogobluesec.tech", 
                            "https://diogobluesec.tech", 
                            "https://app.diogobluesec.tech",
                        )
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);

                // Configuração aberta para o Simulador (qualquer origem pode chamar os mocks)
                // Isso permite que Postman ou outras apps consumam os endpoints criados
                registry.addMapping("/**")
                        .allowedOrigins("*")
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}
