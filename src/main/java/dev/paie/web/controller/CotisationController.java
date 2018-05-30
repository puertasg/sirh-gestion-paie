package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<Cotisation> listerCotisations() {
		return cotisationRepository.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public Cotisation retournerCotisation(@PathVariable(value = "id") int id) {
		Cotisation cotisation = cotisationRepository.findOne(id);

		if (cotisation != null) {
			return cotisation;
		} else {
			throw new ApiItemNotFoundException();
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public void insertCotisation(@RequestBody Cotisation cotisation) {
		cotisationRepository.save(cotisation);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public void updateCotisation(@PathVariable(value = "id") int id, @RequestBody Cotisation cotisation) {
		Cotisation nouvelleCotisation = cotisationRepository.findOne(id);

		nouvelleCotisation.setCode(cotisation.getCode());
		nouvelleCotisation.setLibelle(cotisation.getLibelle());
		nouvelleCotisation.setTauxPatronal(cotisation.getTauxPatronal());
		nouvelleCotisation.setTauxSalarial(cotisation.getTauxSalarial());

		cotisationRepository.save(nouvelleCotisation);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deleteCotisation(@PathVariable(value = "id") int id) {
		cotisationRepository.delete(id);
	}
}
