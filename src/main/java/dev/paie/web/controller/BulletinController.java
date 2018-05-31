package dev.paie.web.controller;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;

@Controller
@RequestMapping(path = "/bulletins")
public class BulletinController {

	@Autowired
	private PeriodeRepository periodeRepository;

	@Autowired
	private RemunerationEmployeRepository remunerationRepository;

	@Autowired
	private BulletinSalaireRepository bulletinSalaireRepository;

	@Autowired
	private CalculerRemunerationService calc;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerBulletin(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		// Création de l'objet du modèle.
		BulletinSalaire bulletin = new BulletinSalaire();
		// Liaison du modèle et de l'objet.
		model.addAttribute("bulletin", bulletin);

		model.addAttribute("listPeriodes", periodeRepository.findAll());
		model.addAttribute("listeRemunerationEmploye", remunerationRepository.findAll());

		// Renvoi du nom logique de la vue formulaire.
		return mv;
	}

	@RequestMapping(method = RequestMethod.GET)
	@Secured({ "ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR" })
	public ModelAndView listerEmploye(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/bulletins");

		model.addAttribute("mapCalculs", calc.mapBulletinResultatCalcul());

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	@Secured({ "ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR" })
	public ModelAndView afficherBulletin(Model model, @PathVariable(value = "id") int id) {

		Map<BulletinSalaire, ResultatCalculRemuneration> bulletinAvecCalcul = calc.mapBulletinResultatCalcul(id);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/bulletin");
		model.addAttribute("bulletinAvecCalcul", bulletinAvecCalcul);

		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public String submitForm(@ModelAttribute("bulletin") BulletinSalaire bulletin) {
		ZonedDateTime dateCreation = ZonedDateTime.of(LocalDateTime.now(), ZoneId.systemDefault());
		bulletin.setDateCreation(dateCreation);

		bulletinSalaireRepository.save(bulletin);
		return "redirect:/mvc/bulletins";
	}
}
