package fi.hh.ohjelmistoprojekti.backend2.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.ohjelmistoprojekti.backend2.domain.Kysely;
import fi.hh.ohjelmistoprojekti.backend2.domain.KyselyRepository;
import fi.hh.swd20.PalvelinHarkkatyo.Domain.Ilmoitus;


@CrossOrigin
@Controller
public class KyselyController {

	
	@RequestMapping("/*")
	public String index() {
		return "index";
	}
	
	
	@Autowired
	KyselyRepository kysRepository;
	
	
	@RequestMapping(value="/kysely", method = RequestMethod.GET)
    public @ResponseBody List<Kysely> KyselyListRest() {	
        return (List<Kysely>) kysRepository.findAll();
    }    
	
	@RequestMapping(value="/kysely/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Kysely> findIlmoitusRest(@PathVariable("id") Long kyselyId) {	
    	return kysRepository.findById(kyselyId);
    }   
	
	@PostMapping(path ="/kysely", consumes = "application/json", produces = "application/json")
    public Kysely saveKyselyRest(@RequestBody Kysely kysely) {	
    	return kysRepository.save(kysely);
    }

}
