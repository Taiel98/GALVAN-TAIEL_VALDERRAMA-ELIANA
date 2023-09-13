package com.backend.GALVANTAIEL_VALDERRAMAELIANA;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GalvanTaielValderramaElianaApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(GalvanTaielValderramaElianaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(GalvanTaielValderramaElianaApplication.class, args);
		LOGGER.info("La aplicación está corriendo en el puerto 8081  (^~^)");
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
