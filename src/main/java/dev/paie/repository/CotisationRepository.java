package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.Cotisation;

//                                               //type de l'objet / type de la clé primaire
public interface CotisationRepository extends JpaRepository<Cotisation, Integer>{
	
	Cotisation findByCode(String code);
	void deleteByCode(String code);
}
