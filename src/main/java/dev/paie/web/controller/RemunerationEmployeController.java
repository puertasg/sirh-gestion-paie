package dev.paie.web.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Collegue;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@Autowired
	private ProfilRemunerationRepository profilRepository;

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private RemunerationEmployeRepository remunerationRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerEmploye(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("prefixMatricule", "M00");
		// Création de l'objet du modèle.
		RemunerationEmploye remEmploye = new RemunerationEmploye();
		// Liaison du modèle et de l'objet.
		model.addAttribute("remEmploye", remEmploye);

		model.addAttribute("listEntreprise", entrepriseRepository.findAll());
		model.addAttribute("listProfils", profilRepository.findAll());
		model.addAttribute("listGrades", gradeRepository.findAll());

		// Renvoi du nom logique de la vue formulaire.
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET)
	@Secured({ "ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR" })
	public ModelAndView listerEmploye(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/employes");

		model.addAttribute("listeRemunerationEmploye", remunerationRepository.findAll());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView submitForm(@ModelAttribute("remEmploye") RemunerationEmploye employe) {
		ModelAndView mv = new ModelAndView();
		RestTemplate rt = new RestTemplate();

		Collegue[] result = rt.getForObject("http://collegues-api.cleverapps.io/collegues?matricule={matricule}",
				Collegue[].class, employe.getMatricule());

		if (result.length > 0) {
			ZonedDateTime dateCreation = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
			employe.setDateCreation(dateCreation);
			remunerationRepository.save(employe);

			mv.setViewName("redirect:/mvc/employes");
		} else {
			mv.addObject("errorMatricule", "error");
			mv.setViewName("redirect:/mvc/employes/creer");
		}

		return mv;
	}
}
