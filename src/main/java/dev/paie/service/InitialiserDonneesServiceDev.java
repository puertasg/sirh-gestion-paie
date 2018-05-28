package dev.paie.service;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService{
	
	@PersistenceContext private EntityManager em;
	
	@Override
	public void initialiser() {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("cotisations-imposables.xml");
		Collection<Cotisation> mapCotisations = context.getBeansOfType(Cotisation.class).values();
		for(Cotisation cotisation : mapCotisations)
		{
			System.out.println("test");
			em.persist(cotisation);
		}
	}
}
