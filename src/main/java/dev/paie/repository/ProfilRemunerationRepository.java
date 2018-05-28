package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.ProfilRemuneration;

//                                               //type de l'objet / type de la clé primaire
public interface ProfilRemunerationRepository extends JpaRepository<ProfilRemuneration, Integer>{
	
}
