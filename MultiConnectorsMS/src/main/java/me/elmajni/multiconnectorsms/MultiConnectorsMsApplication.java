package me.elmajni.multiconnectorsms;

import com.jax.ws.web.service.Compte;
import me.elmajni.multiconnectorsms.dtos.CompteRequestDTO;
import me.elmajni.multiconnectorsms.service.CompteService;
import me.elmajni.multiconnectorsms.soapWebService.CompteRepository;
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
	CommandLineRunner start(CompteRepository compteRepository, CompteService compteService) {
		return args -> {
			compteRepository.getAllComptes().add(new Compte(1L, Math.random() * 9888, new Date()));
			compteRepository.getAllComptes().add(new Compte(2L, Math.random() * 9888, new Date()));
			compteRepository.getAllComptes().add(new Compte(3L, Math.random() * 9888, new Date()));
			compteRepository.getAllComptes().add(new Compte(4L, Math.random() * 9888, new Date()));
			compteRepository.getAllComptes().add(new Compte(5L, Math.random() * 9888, new Date()));

		};
	}

}
