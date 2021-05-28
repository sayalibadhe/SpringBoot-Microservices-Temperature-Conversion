package com.poc8.namingservertemperature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class NamingServerTemperatureApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamingServerTemperatureApplication.class, args);
	}

}
