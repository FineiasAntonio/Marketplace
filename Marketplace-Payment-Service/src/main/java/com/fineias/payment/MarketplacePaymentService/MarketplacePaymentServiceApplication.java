package com.fineias.payment.MarketplacePaymentService;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@EnableRabbit
@SpringBootApplication
public class MarketplacePaymentServiceApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(MarketplacePaymentServiceApplication.class);
		app.setDefaultProperties(Collections
				.singletonMap("server.port", "8081"));
		app.run(args);
	}

}
