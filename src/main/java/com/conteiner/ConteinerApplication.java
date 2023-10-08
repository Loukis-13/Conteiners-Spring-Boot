package com.conteiner;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info=@Info(title = "Contêiners")
)
@SpringBootApplication
public class ConteinerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConteinerApplication.class, args);
	}

}
