package dev.paie.service;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService{
	
	@PersistenceContext private EntityManager em;
	
	@Override
	public void initialiser() {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cotisations-imposables.xml", "cotisations-non-imposables.xml", "entreprises.xml", "grades.xml", "profils-remuneration.xml");
		Collection<Cotisation> collecCotisations = context.getBeansOfType(Cotisation.class).values();
		Collection<Entreprise> collecEntreprises = context.getBeansOfType(Entreprise.class).values();
		for(Cotisation cotisation : collecCotisations)
		{
			em.persist(cotisation);
		}
		
		for(Entreprise entreprise : collecEntreprises)
		{
			em.persist(entreprise);
		}
	}
}
