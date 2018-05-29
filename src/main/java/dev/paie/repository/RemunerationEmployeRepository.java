package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.RemunerationEmploye;

//                                               //type de l'objet / type de la clé primaire
public interface RemunerationEmployeRepository extends JpaRepository<RemunerationEmploye, Integer>{
	
}
