/**
 * Configuración para comunicación con el microservicio de inventario
 * para validación de stock y realizar registro de orden correctamente
 */
package com.mservicesdev.orders_mservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient.Builder webClient() {
        return WebClient.builder();
    }

}
