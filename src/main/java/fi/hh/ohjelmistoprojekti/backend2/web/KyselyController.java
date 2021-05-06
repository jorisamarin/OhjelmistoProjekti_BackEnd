package fi.hh.ohjelmistoprojekti.backend2.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.ohjelmistoprojekti.backend2.domain.Kysely;
import fi.hh.ohjelmistoprojekti.backend2.domain.KyselyRepository;
import fi.hh.ohjelmistoprojekti.backend2.domain.Kysymys;
import fi.hh.ohjelmistoprojekti.backend2.domain.KysymysRepository;
import fi.hh.ohjelmistoprojekti.backend2.domain.Vastaus;
import fi.hh.ohjelmistoprojekti.backend2.domain.VastausRepository;




@CrossOrigin
@Controller
public class KyselyController {

	
	@RequestMapping("/*")
	public String index() {
		return "index";
	}
	
	
	@Autowired
	KyselyRepository kysRepository;
	
	@Autowired
	KysymysRepository kysymysRepo;
	
	@Autowired
	VastausRepository vastausRepo;
	
	
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
		try {
			return kysRepository.save(kysely);
		} catch (Exception e) {
			System.out.println("virhe");
			return null;
		}
  
    }
	
	@RequestMapping(value="/kysymys/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Kysymys> findKysymysRest(@PathVariable("id") Long kysymysId) {	
    	return kysymysRepo.findById(kysymysId);
    } 
	
	@PostMapping(path = "/kysymys", consumes = "application/json", produces = "application/json")
	public Vastaus saveVastausRest(@RequestBody Vastaus vastaus) {
		return vastausRepo.save(vastaus);
	}
	
	@RequestMapping(value = "/addkysymys", method = RequestMethod.POST)
	public String saveKysymys(@ModelAttribute Kysymys kysymys) {
		kysymysRepo.save(kysymys);
		return "redirect:/index";
	}
	
	@RequestMapping(value = "/addkysymys", method = RequestMethod.GET)
	public String getUusiKysymys(Model model) {
		model.addAttribute("kysymys", new Kysymys());
		model.addAttribute("kysymystyyppi", kysymysRepo.findAll());
		return "addkysymys";
	}
	
	@RequestMapping(value = "/deletekysymys/{id}", method = RequestMethod.GET)
	public String deleteKysymys(@PathVariable("kysymys_id") Long kysymysId) {
		kysymysRepo.deleteById(kysymysId);
		return "redirect:../addkysymys";
	}
	
	@RequestMapping(value = "/editkysymys/{id}", method = RequestMethod.GET)
	public String editKysymys(@PathVariable("kysymys_id") Long kysymysId, Model model) {
		model.addAttribute("kysymys", kysymysRepo.findById(kysymysId));
		model.addAttribute("kysymystyyppi", kysymysRepo.findAll());
		return "editkysymys";
	}
	
	@RequestMapping(value = "/kysymyslist", method = RequestMethod.GET)
	public String getKysymykset(Model model) {
			List<Kysymys> kysymykset =  (List<Kysymys>) kysymysRepo.findAll();
			model.addAttribute("kysymykset", kysymykset); 
			return "kysymyslist"; 
								
	}

}
