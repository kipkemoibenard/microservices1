package com.microservices.departmentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DepartmentserviceApplication {
	//	https://youtu.be/HFl2dzhVuUo updated
	public static void main(String[] args) {
		SpringApplication.run(DepartmentserviceApplication.class, args);
	}

}
