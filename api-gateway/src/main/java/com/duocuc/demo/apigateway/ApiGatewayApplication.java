package com.duocuc.demo.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);

		System.out.println("=============================================");
		System.out.println("API Gateway iniciado correctamente");
		System.out.println("URL: http://localhost:8099");
		System.out.println("----------------------------------------------");
		System.out.println(" /api/comentario/**   ---> COMENTARIO");
		System.out.println(" /api/compra/**       ---> COMPRA");
		System.out.println(" /api/descuento/**    ---> DESCUENTO");
		System.out.println(" /api/informe/**      ---> INFORME");
		System.out.println(" /api/mensaje/**      ---> MENSAJE");
		System.out.println(" /api/productos/**    ---> PRODUCTOS");
		System.out.println(" /api/usuario/**      ---> USUARIO");
		System.out.println("-----------------------------------------------");
		System.out.println("Eureka: http://localhost:8761");
		System.out.println("==============================================");
	}

}
