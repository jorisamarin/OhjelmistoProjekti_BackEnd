package fi.hh.ohjelmistoprojekti.backend2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.ohjelmistoprojekti.backend2.domain.Kysely;
import fi.hh.ohjelmistoprojekti.backend2.domain.KyselyRepository;
import fi.hh.ohjelmistoprojekti.backend2.domain.Kysymys;
import fi.hh.ohjelmistoprojekti.backend2.domain.KysymysRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);	
	}
	
	@Bean
	public CommandLineRunner test(KyselyRepository kysrepository, KysymysRepository kysymysRepo) {
		return (args) -> {
			
		

			kysrepository.save(new Kysely("Moi"));
			kysrepository.save(new Kysely("Hei"));
			
			kysymysRepo.save(new Kysymys("avoinKysymys", "Paljon sun outfit maksaa?"));
			
			
			
			
		};
		
	}
	
}