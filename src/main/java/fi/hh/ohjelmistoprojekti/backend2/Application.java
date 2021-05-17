package fi.hh.ohjelmistoprojekti.backend2;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import fi.hh.ohjelmistoprojekti.backend2.domain.Kysely;
import fi.hh.ohjelmistoprojekti.backend2.domain.KyselyRepository;
import fi.hh.ohjelmistoprojekti.backend2.domain.Kysymys;
import fi.hh.ohjelmistoprojekti.backend2.domain.KysymysRepository;
import fi.hh.ohjelmistoprojekti.backend2.domain.User;
import fi.hh.ohjelmistoprojekti.backend2.domain.UserRepository;
import fi.hh.ohjelmistoprojekti.backend2.domain.Vastaus;
import fi.hh.ohjelmistoprojekti.backend2.domain.VastausRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);	
	}
	
	@Bean
	public CommandLineRunner test(VastausRepository vastausRepo, KyselyRepository kysrepository, KysymysRepository kysymysRepo, UserRepository uRepository) {
		return (args) -> {
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			
			uRepository.save(user1);
			uRepository.save(user2);
			
			Kysely kyse1 = new Kysely("hyvinvointiKysely", user2);
			Kysely kyse2 = new Kysely("pahoinvointiKysely", user2);
			
			kysrepository.save(kyse1);
			kysrepository.save(kyse2);
			
			Kysymys kyse1Kysymys1 = new Kysymys("avoinKysymys", "Voitko hyvin?");
			kyse1Kysymys1.setKysely(kyse1);
			Kysymys kyse1Kysymys2 = new Kysymys("avoinKysymys", "Jos voit hyvin, niin miksi ?");
			kyse1Kysymys2.setKysely(kyse1);
			Kysymys kyse1Kysymys3 = new Kysymys("avoinKysymys", "Avoin sana!");
			kyse1Kysymys3.setKysely(kyse1);
			
			Kysymys kyse2Kysymys1 = new Kysymys("avoinKysymys", "Voitko pahoin?");
			kyse2Kysymys1.setKysely(kyse2);
			Kysymys kyse2Kysymys2 = new Kysymys("avoinKysymys", "Jos voit pahoin, niin miksi ?");
			kyse2Kysymys2.setKysely(kyse2);
			Kysymys kyse2Kysymys3 = new Kysymys("avoinKysymys", "Avoin sana!");
			kyse2Kysymys3.setKysely(kyse2);
			
			kysymysRepo.save(kyse1Kysymys1);
			kysymysRepo.save(kyse1Kysymys2);
			kysymysRepo.save(kyse1Kysymys3);
			
			kysymysRepo.save(kyse2Kysymys1);
			kysymysRepo.save(kyse2Kysymys2);
			kysymysRepo.save(kyse2Kysymys3);
			
			Vastaus kyse1kysymys1vastaus1 = new Vastaus("En", kyse1Kysymys1);
			Vastaus kyse1kysymys1vastaus2 = new Vastaus("Joo", kyse1Kysymys1);
			Vastaus kyse1kysymys1vastaus3 = new Vastaus("En osaa sanoa", kyse1Kysymys1);
			
			Vastaus kyse1kysymys2vastaus1 = new Vastaus("en tieda1", kyse1Kysymys2);
			Vastaus kyse1kysymys2vastaus2 = new Vastaus("en tieda2", kyse1Kysymys2);
			Vastaus kyse1kysymys2vastaus3 = new Vastaus("en tieda3", kyse1Kysymys2);
			
			Vastaus kyse1kysymys3vastaus1 = new Vastaus("En", kyse1Kysymys3);
			Vastaus kyse1kysymys3vastaus2 = new Vastaus("Joo", kyse1Kysymys3);
			Vastaus kyse1kysymys3vastaus3 = new Vastaus("En osaa sanoa", kyse1Kysymys3);
			
			Vastaus kyse2kysymys1vastaus1 = new Vastaus("asd", kyse2Kysymys1);
			Vastaus kyse2kysymys1vastaus2 = new Vastaus("asdasd", kyse2Kysymys1);
			Vastaus kyse2kysymys1vastaus3 = new Vastaus("asdasdasd", kyse2Kysymys1);
			
			Vastaus kyse2kysymys2vastaus1 = new Vastaus("en tieda1", kyse2Kysymys2);
			Vastaus kyse2kysymys2vastaus2 = new Vastaus("en tieda2", kyse2Kysymys2);
			Vastaus kyse2kysymys2vastaus3 = new Vastaus("en tieda3", kyse2Kysymys2);
			
			Vastaus kyse2kysymys3vastaus1 = new Vastaus("asd", kyse2Kysymys3);
			Vastaus kyse2kysymys3vastaus2 = new Vastaus("asdasd", kyse2Kysymys3);
			Vastaus kyse2kysymys3vastaus3 = new Vastaus("asdasdasd", kyse2Kysymys3);
			
			vastausRepo.save(kyse1kysymys1vastaus1);
			vastausRepo.save(kyse1kysymys1vastaus2);
			vastausRepo.save(kyse1kysymys1vastaus3);
			
			vastausRepo.save(kyse1kysymys2vastaus1);
			vastausRepo.save(kyse1kysymys2vastaus2);
			vastausRepo.save(kyse1kysymys2vastaus3);
			
			vastausRepo.save(kyse1kysymys3vastaus1);
			vastausRepo.save(kyse1kysymys3vastaus2);
			vastausRepo.save(kyse1kysymys3vastaus3);
			
			vastausRepo.save(kyse2kysymys1vastaus1);
			vastausRepo.save(kyse2kysymys1vastaus2);
			vastausRepo.save(kyse2kysymys1vastaus3);
			
			vastausRepo.save(kyse2kysymys2vastaus1);
			vastausRepo.save(kyse2kysymys2vastaus2);
			vastausRepo.save(kyse2kysymys2vastaus3);
			
			vastausRepo.save(kyse2kysymys3vastaus1);
			vastausRepo.save(kyse2kysymys3vastaus2);
			vastausRepo.save(kyse2kysymys3vastaus3);
			
			
			
			
			
			
			

			
			
			kysymysRepo.save(new Kysymys("RadioKysymys", "Testi"));
			
			
			
		};
		
	}
	
}