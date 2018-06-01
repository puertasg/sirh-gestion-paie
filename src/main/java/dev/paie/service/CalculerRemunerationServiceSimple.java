package dev.paie.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.util.PaieUtils;
import dev.paie.util.ResultatCalculBulletin;

@Component
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired
	private BulletinSalaireRepository bulletinSalaireRepository;

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		PaieUtils paieUtils = new PaieUtils();

		// Récupération des listes de cotisations
		List<Cotisation> listCotisationsNonImposables = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables();

		List<Cotisation> listCotisationsImposables = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsImposables();
		// Fin récupération listes

		Grade grade = bulletin.getRemunerationEmploye().getGrade();

		// Calcul salaires
		BigDecimal salaireBase = grade.getNbHeuresBase().multiply(grade.getTauxBase());
		String salaireBaseFormat = paieUtils.formaterBigDecimal(salaireBase);

		BigDecimal salaireBrut = salaireBase.add(bulletin.getPrimeExceptionnelle());
		String salaireBrutFormat = paieUtils.formaterBigDecimal(salaireBrut);

		// Calcul total retenue salariale
		BigDecimal totalRetenueSalariale = listCotisationsNonImposables.stream()
				.filter(cot -> cot.getTauxSalarial() != null).map(cot -> cot.getTauxSalarial().multiply(salaireBrut))
				.reduce(BigDecimal::add).get();
		String totalRetenueSalarialeFormat = paieUtils.formaterBigDecimal(totalRetenueSalariale);
		
		//Calcul total taux salarial
		BigDecimal totalTauxSalarial = listCotisationsNonImposables.stream()
				.filter(cot -> cot.getTauxSalarial() != null).map(cot -> cot.getTauxSalarial())
				.reduce(BigDecimal::add).get();
		String totalTauxSalarialFormat = paieUtils.formaterBigDecimal(totalTauxSalarial);

		// Calcul total cotisations patronales
		BigDecimal totalCotisationsPatronales = listCotisationsNonImposables.stream()
				.filter(cot -> cot.getTauxPatronal() != null).map(cot -> cot.getTauxPatronal().multiply(salaireBrut))
				.reduce(BigDecimal::add).get();
		String totalCotisationsPatronalesFormat = paieUtils.formaterBigDecimal(totalCotisationsPatronales);
		
		//Calcul total cotisations patronales imposables
		BigDecimal totalCotisationsPatronalesImposables = new BigDecimal("0");
		for(Cotisation cotisationImposable : listCotisationsImposables)
		{
			if(cotisationImposable.getTauxPatronal() != null)
			{
				totalCotisationsPatronalesImposables.add(cotisationImposable.getTauxPatronal().multiply(salaireBrut));
			}
		}
		
		String totalCotisationsPatronalesImposablesFormat = paieUtils.formaterBigDecimal(totalCotisationsPatronalesImposables);
		
		// Calcul net imposable
		// De préférence passer par un nouvel objet BigDecimal pour un calcul
		BigDecimal netImposable = new BigDecimal(salaireBrutFormat)
				.subtract(new BigDecimal(totalRetenueSalarialeFormat));
		String netImposableFormat = paieUtils.formaterBigDecimal(netImposable);

		// Calcul net à payer
		BigDecimal totalCotisationsImposables = listCotisationsImposables.stream()
				.filter(cot -> cot.getTauxSalarial() != null).map(cot -> cot.getTauxSalarial().multiply(salaireBrut))
				.reduce(BigDecimal::add).get();
		String totalCotisationsImposablesFormat = paieUtils.formaterBigDecimal(totalCotisationsImposables);
		BigDecimal netAPayer = netImposable.subtract(totalCotisationsImposables);
		String netAPayerFormat = paieUtils.formaterBigDecimal(netAPayer);

		// Instanciation ResultatCalculRemuneration et set des valeurs
		ResultatCalculRemuneration resultat = new ResultatCalculRemuneration();
		resultat.setSalaireDeBase(salaireBaseFormat);
		resultat.setSalaireBrut(salaireBrutFormat);
		resultat.setTotalRetenueSalarial(totalRetenueSalarialeFormat);
		resultat.setTotalTauxSalarial(totalTauxSalarialFormat);
		resultat.setTotalCotisationsPatronales(totalCotisationsPatronalesFormat);
		resultat.setTotalCotisationsPatronalesImposables(totalCotisationsPatronalesImposablesFormat);
		resultat.setTotalCotisationsImposables(totalCotisationsImposablesFormat);
		resultat.setNetImposable(netImposableFormat);
		resultat.setNetAPayer(netAPayerFormat);

		return resultat;
	}

	@Transactional
	public List<ResultatCalculBulletin> calculerListeBulletin() {

		List<BulletinSalaire> listeBulletins = bulletinSalaireRepository.findAll();
		List<ResultatCalculBulletin> listeResultat = new ArrayList<>();
		for (BulletinSalaire bulletin : listeBulletins) {
			ResultatCalculRemuneration resCalc = this.calculer(bulletin);

			ResultatCalculBulletin resBulletin = new ResultatCalculBulletin(bulletin, resCalc);

			listeResultat.add(resBulletin);
		}

		return listeResultat;
	}

	@Transactional
	public ResultatCalculBulletin calculerBulletin(int id) {

		BulletinSalaire bulletin = bulletinSalaireRepository.findOne(id);
		ResultatCalculRemuneration resCalc = this.calculer(bulletin);
		ResultatCalculBulletin resBulletin = new ResultatCalculBulletin(bulletin, resCalc);

		return resBulletin;
	}
}
