package de.LucaR.Fahrradverleih;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FahrradverleihApplication {

	public static void main(String[] args) {
		SpringApplication.run(FahrradverleihApplication.class, args);
	}

}
