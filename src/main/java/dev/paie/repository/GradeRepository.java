package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.Avantage;
import dev.paie.entite.Grade;

//                                               //type de l'objet / type de la clé primaire
public interface GradeRepository extends JpaRepository<Grade, Integer>{
	
}
