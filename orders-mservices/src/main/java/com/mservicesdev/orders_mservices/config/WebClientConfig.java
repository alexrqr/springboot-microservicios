/**
 * Configuración para comunicación con el microservicio de inventario
 * para validación de stock y realizar registro de orden correctamente
 */
package com.mservicesdev.orders_mservices.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    @LoadBalanced // Equilibrado de carga automático al realizar solicitudes HTTP
    public WebClient.Builder webClient() {
        return WebClient.builder();
    }

}
