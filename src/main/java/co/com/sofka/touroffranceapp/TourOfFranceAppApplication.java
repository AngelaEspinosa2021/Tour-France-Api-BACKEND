package co.com.sofka.touroffranceapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class TourOfFranceAppApplication {

	public static void main(String[] args) {

		SpringApplication.run(TourOfFranceAppApplication.class, args);
		//System.out.println(UUID.randomUUID().toString().toUpperCase().substring(0,3));
	}

}
