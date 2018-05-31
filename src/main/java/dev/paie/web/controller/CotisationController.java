package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Cotisation;
import dev.paie.exceptions.ApiItemNotFoundException;
import dev.paie.repository.CotisationRepository;

@RestController
@RequestMapping(path = "/api/cotisations")
public class CotisationController {

	@Autowired
	private CotisationRepository cotisationRepository;

	@RequestMapping(method = RequestMethod.GET)
	@Secured({ "ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR" })
	public List<Cotisation> listerCotisations() {
		return cotisationRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{code}")
	@Secured({ "ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR" })
	public Cotisation retournerCotisation(@PathVariable(value = "code") String code) {
		Cotisation cotisation = cotisationRepository.findByCode(code);

		if (cotisation != null) {
			return cotisation;
		} else {
			throw new ApiItemNotFoundException();
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	@Secured("ROLE_ADMINISTRATEUR")
	public void insertCotisation(@RequestBody Cotisation cotisation) {
		cotisationRepository.save(cotisation);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{code}")
	@Secured("ROLE_ADMINISTRATEUR")
	public void updateCotisation(@PathVariable(value = "code") String code, @RequestBody Cotisation cotisation) {
		Cotisation nouvelleCotisation = cotisationRepository.findByCode(code);

		nouvelleCotisation.setCode(cotisation.getCode());
		nouvelleCotisation.setLibelle(cotisation.getLibelle());
		nouvelleCotisation.setTauxPatronal(cotisation.getTauxPatronal());
		nouvelleCotisation.setTauxSalarial(cotisation.getTauxSalarial());

		cotisationRepository.save(nouvelleCotisation);
	}

	@Transactional
	@RequestMapping(method = RequestMethod.DELETE, value = "/{code}")
	@Secured("ROLE_ADMINISTRATEUR")
	public void deleteCotisation(@PathVariable(value = "code") String code) {
		cotisationRepository.deleteByCode(code);
	}
}
