package me.elmajni.multiconnectorsms;

import me.elmajni.multiconnectorsms.dtos.CompteRequestDTO;
import me.elmajni.multiconnectorsms.service.CompteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class MultiConnectorsMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MultiConnectorsMsApplication.class, args);
	}

	@Bean
	CommandLineRunner start(CompteService compteService){
		return args -> {
			compteService.saveCompte(new CompteRequestDTO(null,new Date(),Math.random()*20000,"Euro"));
			compteService.saveCompte(new CompteRequestDTO(null,new Date(),Math.random()*20000,"DH"));
			compteService.saveCompte(new CompteRequestDTO(null,new Date(),Math.random()*20000,"Euro"));
			compteService.saveCompte(new CompteRequestDTO(null,new Date(),Math.random()*20000,"DH"));

		};
	}

}
