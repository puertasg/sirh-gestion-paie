package dev.paie.util;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;

public class ResultatCalculBulletin {
	
	private BulletinSalaire bulletin;
	private ResultatCalculRemuneration resultatCalculRemuneration;
	
	public ResultatCalculBulletin(BulletinSalaire b, ResultatCalculRemuneration res)
	{
		this.bulletin = b;
		this.resultatCalculRemuneration = res;
	}

	public BulletinSalaire getBulletin() {
		return bulletin;
	}

	public void setBulletin(BulletinSalaire bulletin) {
		this.bulletin = bulletin;
	}

	public ResultatCalculRemuneration getResultatCalculRemuneration() {
		return resultatCalculRemuneration;
	}

	public void setResultatCalculRemuneration(ResultatCalculRemuneration resultatCalculRemuneration) {
		this.resultatCalculRemuneration = resultatCalculRemuneration;
	}
}
