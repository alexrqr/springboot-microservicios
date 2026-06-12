package com.mservicesdev.discovery_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {
	/**
	 * Eureka -Netflix Server
	 * Simplifica el registro y descubrimiento de servicios permitiendo
	 * que los microservicios se comuniquen entre sí de forma dinámica y escalable
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryServerApplication.class, args);
	}

}
