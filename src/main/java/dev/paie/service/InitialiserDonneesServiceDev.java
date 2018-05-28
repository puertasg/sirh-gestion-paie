package dev.paie.service;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService{
	
	@Autowired
	private ApplicationContext context;
	
	@PersistenceContext private EntityManager em;
	
	@Override
	public void initialiser() {
		Collection<Cotisation> mapCotisations = context.getBeansOfType(Cotisation.class).values();
		for(Cotisation cotisation : mapCotisations)
		{
			em.persist(cotisation);
		}
	}
}
