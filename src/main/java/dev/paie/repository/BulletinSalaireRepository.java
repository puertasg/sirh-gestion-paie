package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.BulletinSalaire;

//                                               //type de l'objet / type de la clé primaire
public interface BulletinSalaireRepository extends JpaRepository<BulletinSalaire, Integer>{
	
}
