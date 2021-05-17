package fi.hh.ohjelmistoprojekti.backend2.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface KyselyRepository extends CrudRepository<Kysely, Long> {
	
	Kysely findByNimi(String nimi);
	
	List<Kysely> findByUser (User user);

}
