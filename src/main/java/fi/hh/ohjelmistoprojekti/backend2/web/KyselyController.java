package fi.hh.ohjelmistoprojekti.backend2.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
import fi.hh.ohjelmistoprojekti.backend2.domain.User;
import fi.hh.ohjelmistoprojekti.backend2.domain.UserRepository;
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
	
	@Autowired
	UserRepository userRepo;
	
	//Samulin tekemät controllerit
	
	//TODO
	//POST metodi vastauskselle tekemättä -done
	//THYMELEAF METODIT EI TOIMI TÄYSIN
	//addKysymys ja savekysymys tarvis tiedot siitä kyselystä mihin kysymys tulee ja lisätä se kysymykseen (jotenkin endpointtien kautta ehkä?)
	//ADMINILLE VAIN OIKEUDET 
	
	//AAMUN KOMMENTIT VIELÄ!!
	//THYMELEAF addkysymys ja savekysymys toimii mutta haluttu kysely pitää valita deplististä -done
	//Osassa thymeleaf sivuja ei toimi CSS
	
	
	
	@RequestMapping(value="/kysely", method = RequestMethod.GET)
    public @ResponseBody List<Kysely> KyselyListRest() {	
        return (List<Kysely>) kysRepository.findAll();
    }    
	
	@RequestMapping(value="/kysymykset/{nimi}", method = RequestMethod.GET)
	public @ResponseBody List<Kysymys> KysymysListRest(@PathVariable("nimi") String nimi) {
		Kysely kysely = kysRepository.findByNimi(nimi);
		return (List<Kysymys>) kysymysRepo.findByKysely(kysely);
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
	
	//JONIN LISÄÄMÄT
	
	@RequestMapping(value="/vastaus", method = RequestMethod.GET)
    public @ResponseBody List<Vastaus> VastausListRest() {	
        return (List<Vastaus>) vastausRepo.findAll();
    }  
	
	@PostMapping(path ="/vastaus", consumes = "application/json", produces = "application/json")
    public Vastaus saveVastausRest(@RequestBody Vastaus vastaus) {	
		try {
			return vastausRepo.save(vastaus);
		} catch (Exception e) {
			System.out.println("virhe");
			return null;
		}
  
    }
	@RequestMapping(value = "/kyselylist", method = RequestMethod.GET)
	public String getIlmoitukset(Model model) {
			List<Kysely> kyselyt =  (List<Kysely>) kysRepository.findAll();
			model.addAttribute("kyselyt", kyselyt); 
			return "ilmoituslist"; 
								
	}
	
	@RequestMapping(value = "/addkysymys", method = RequestMethod.GET)
	public String getUusiKysymys(Model model) {
		model.addAttribute("kysymys", new Kysymys());
		model.addAttribute("kysely", kysRepository.findAll());
		return "addkysymys";
	}
	
	//ADMIN THYMELEAF JUTTUJA
	
	@RequestMapping("/userKyselyt")
	public String userKyselyt(Model model) {
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		User usernow = userRepo.findByUsername(username);
		model.addAttribute("kysely", kysRepository.findByUser(usernow));
		return "userKyselyt";
	}
	
	@RequestMapping(value = "/addKysely")
	public String addKysely(Model model){
		model.addAttribute("kysely", new Kysely());
		return "addKysely";
	}
	
	@RequestMapping(value = "/saveKysely", method = RequestMethod.POST)
	public String saveKysely(Kysely kysely){
		UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		User usernow = userRepo.findByUsername(username);
		kysely.setUser(usernow);
		kysRepository.save(kysely);
		return "redirect:userKyselyt";
	}
	
	@RequestMapping("/kyselyKysymykset/{nimi}")
	public String kyselyKysymykset(@PathVariable("nimi") String nimi, Model model){
		Kysely kysely = kysRepository.findByNimi(nimi);
		model.addAttribute("kysymys", kysymysRepo.findByKysely(kysely));
		return "kyselyKysymykset";
	}
	
	@RequestMapping(value = "/addKysymys")
	public String addKysymys(Model model){
		model.addAttribute("kysymys", new Kysymys());
		model.addAttribute("kyselyt", kysRepository.findAll());
		return "addKysymys";
	}
	
	@RequestMapping(value = "/saveKysymys", method = RequestMethod.POST)
	public String saveKysymys2(Kysymys kysymys){
		kysymysRepo.save(kysymys);
		return "redirect:userKyselyt";
	}
	
	//Muut controllerit
	
	//LOPUT SIIS EI MUN KÄYTTÄMIÄ T SAMULI ELI IHA MYSTEERIKAMAA
	
	
	
	
	
	@RequestMapping(value="/kysymys/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Kysymys> findKysymysRest(@PathVariable("id") Long kysymysId) {	
    	return kysymysRepo.findById(kysymysId);
    } 
	
	//@PostMapping(path = "/kysymys", consumes = "application/json", produces = "application/json")
	//public Vastaus saveVastausRest(@RequestBody Vastaus vastaus) {
		//return vastausRepo.save(vastaus);
	//}
	
	@RequestMapping(value = "/addkysymys", method = RequestMethod.POST)
	public String saveKysymys(@ModelAttribute Kysymys kysymys) {
		kysymysRepo.save(kysymys);
		return "redirect:/index";
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
