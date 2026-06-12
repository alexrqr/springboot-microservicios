package com.mservicesdev.api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiGatewayApplication {
	/**
	 * Spring Cloude GateWay
	 * Permiten gesitonar y enrutar las solicitudes de manera centralizada
	 * además de ofrecer el balanceador de carga.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
