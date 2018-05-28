package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@Autowired
	private ProfilRemunerationRepository profilRepository;
	
	@Autowired
	private GradeRepository gradeRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("prefixMatricule", "M00");
		// Création de l'objet du modèle.
		RemunerationEmploye remEmploye = new RemunerationEmploye();
		// Liaison du modèle et de l'objet.
		model.addAttribute("remEmploye", remEmploye);
		
		//Récupère la liste des entreprises
		List<Entreprise> listEntreprise = entrepriseRepository.findAll();
		model.addAttribute("listEntreprise", listEntreprise);
		
		//Récupère la liste de profils
		List<ProfilRemuneration> listProfils = profilRepository.findAll();
		model.addAttribute("listProfils", listProfils);
		
		//Récupère la liste de grades
		List<Grade> listGrades = gradeRepository.findAll();
		model.addAttribute("listGrades", listGrades);
		
		// Renvoi du nom logique de la vue formulaire.
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/inserer")
	public void insererEmploye(Model model)
	{
		System.out.println("test");
	}
}
