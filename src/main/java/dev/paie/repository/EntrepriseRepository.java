package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.Entreprise;

//                                               //type de l'objet / type de la clé primaire
public interface EntrepriseRepository extends JpaRepository<Entreprise, Integer>{
	
}
