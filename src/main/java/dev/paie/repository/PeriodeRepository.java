package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.Periode;

//                                               //type de l'objet / type de la clé primaire
public interface PeriodeRepository extends JpaRepository<Periode, Integer>{
	
}
