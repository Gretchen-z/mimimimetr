package ru.gretchen.mimimimetr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class MimimimetrApplication {

	public static void main(String[] args) {
		SpringApplication.run(MimimimetrApplication.class, args);
	}

}
