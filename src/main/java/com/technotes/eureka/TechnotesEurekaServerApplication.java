package com.technotes.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class TechnotesEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnotesEurekaServerApplication.class, args);
	}

}
