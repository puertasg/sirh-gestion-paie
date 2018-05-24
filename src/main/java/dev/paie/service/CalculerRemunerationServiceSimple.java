package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Component
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService{
	
	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		PaieUtils paieUtils = new PaieUtils();
		Grade grade = bulletin.getRemunerationEmploye().getGrade();
		
		BigDecimal salaireBase = grade.getNbHeuresBase().multiply(grade.getTauxBase());
		String salaireBaseFormat = paieUtils.formaterBigDecimal(salaireBase);
		
		BigDecimal salaireBrut = salaireBase.add(bulletin.getPrimeExceptionnelle());
		String salaireBrutFormat = salaireBaseFormat + paieUtils.formaterBigDecimal(salaireBrut);
		
		List<Cotisation> listCotisationsNonImposables = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisationsNonImposables();
		BigDecimal totalRetenueSalariale = listCotisationsNonImposables.stream().filter(cot -> cot.getTauxSalarial() != null).map(cot -> cot.getTauxSalarial().multiply(salaireBrut)).reduce(BigDecimal::add).get();
		String totalRetenueSalarialeFormat = paieUtils.formaterBigDecimal(totalRetenueSalariale);
		
		//List<Cotisation> listCotisationImposables
		
		System.out.println(totalRetenueSalarialeFormat);
		
		return new ResultatCalculRemuneration();
	}

}
