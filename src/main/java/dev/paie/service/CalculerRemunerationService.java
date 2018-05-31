package dev.paie.service;

import java.util.List;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.ResultatCalculBulletin;

public interface CalculerRemunerationService {
	ResultatCalculRemuneration calculer(BulletinSalaire bulletin);

	List<ResultatCalculBulletin> calculerListeBulletin();

	ResultatCalculBulletin calculerBulletin(int id);
}
