package fi.hh.ohjelmistoprojekti.backend2.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface KysymysRepository extends CrudRepository<Kysymys, Long> {
	
	List<Kysymys> findByKysely (Kysely kysely);

}