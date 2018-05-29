package dev.paie.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.util.PaieUtils;

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

		// Calcul total cotisations patronales
		BigDecimal totalCotisationsPatronales = listCotisationsNonImposables.stream()
				.filter(cot -> cot.getTauxPatronal() != null).map(cot -> cot.getTauxPatronal().multiply(salaireBrut))
				.reduce(BigDecimal::add).get();
		String totalCotisationsPatronalesFormat = paieUtils.formaterBigDecimal(totalCotisationsPatronales);

		// Calcul net imposable
		// De préférence passer par un nouvel objet BigDecimal pour un calcul
		BigDecimal netImposable = new BigDecimal(salaireBrutFormat)
				.subtract(new BigDecimal(totalRetenueSalarialeFormat));
		String netImposableFormat = paieUtils.formaterBigDecimal(netImposable);

		// Calcul net à payer
		BigDecimal totalCotisationsImposables = listCotisationsImposables.stream()
				.filter(cot -> cot.getTauxSalarial() != null).map(cot -> cot.getTauxSalarial().multiply(salaireBrut))
				.reduce(BigDecimal::add).get();
		BigDecimal netAPayer = netImposable.subtract(totalCotisationsImposables);
		String netAPayerFormat = paieUtils.formaterBigDecimal(netAPayer);

		// Instanciation ResultatCalculRemuneration et set des valeurs
		ResultatCalculRemuneration resultat = new ResultatCalculRemuneration();
		resultat.setSalaireDeBase(salaireBaseFormat);
		resultat.setSalaireBrut(salaireBrutFormat);
		resultat.setTotalRetenueSalarial(totalRetenueSalarialeFormat);
		resultat.setTotalCotisationsPatronales(totalCotisationsPatronalesFormat);
		resultat.setNetImposable(netImposableFormat);
		resultat.setNetAPayer(netAPayerFormat);

		return resultat;
	}

	@Transactional
	public Map<BulletinSalaire, ResultatCalculRemuneration> mapBulletinResultatCalcul() {

		List<BulletinSalaire> listeBulletins = bulletinSalaireRepository.findAll();
		Map<BulletinSalaire, ResultatCalculRemuneration> map = new HashMap<BulletinSalaire, ResultatCalculRemuneration>();
		for (BulletinSalaire bulletin : listeBulletins) {
			ResultatCalculRemuneration resCalc = this.calculer(bulletin);
			map.put(bulletin, resCalc);
		}

		return map;
	}
}
