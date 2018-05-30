package dev.paie.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void initialiser() {

		/*
		 * ClassPathXmlApplicationContext context = new
		 * ClassPathXmlApplicationContext("cotisations-imposables.xml",
		 * "cotisations-non-imposables.xml", "entreprises.xml", "grades.xml",
		 * "profils-remuneration.xml"); Collection<Cotisation> collecCotisations
		 * = context.getBeansOfType(Cotisation.class).values();
		 * Collection<Entreprise> collecEntreprises =
		 * context.getBeansOfType(Entreprise.class).values(); Collection<Grade>
		 * collecGrades = context.getBeansOfType(Grade.class).values();
		 * Collection<ProfilRemuneration> collecProfilsRemuneration =
		 * context.getBeansOfType(ProfilRemuneration.class) .values();
		 * 
		 * insertCollec(collecCotisations); insertCollec(collecEntreprises);
		 * insertCollec(collecGrades); insertCollec(collecProfilsRemuneration);
		 * 
		 * insertPeriode();
		 * 
		 * //Stream.of(Cotisation.class, Entreprise.class, Grade.class,
		 * ProfilRemuneration.class).flatMap(uneClasse ->
		 * context.getBeansOfType(uneClasse).values().stream()).foreach(em::
		 * persist);
		 * 
		 * context.close();
		 */

		try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("cotisations-imposables.xml",
				"cotisations-non-imposables.xml", "entreprises.xml", "grades.xml", "profils-remuneration.xml")) {

			Stream.of(Entreprise.class, Grade.class, Cotisation.class, ProfilRemuneration.class)
					.flatMap(uneClasse -> ctx.getBeansOfType(uneClasse).values().stream()).forEach(em::persist);

			IntStream.rangeClosed(1, 12).mapToObj(i -> {
				Periode p = new Periode();
				p.setDateDebut(LocalDate.of(LocalDate.now().getYear(), i, 1));
				p.setDateFin(p.getDateDebut().with(TemporalAdjusters.lastDayOfMonth()));
				return p;
			}).forEach(em::persist);

		}

		// Désormais gérer par try() => Concept Try-with-resources de Java 7
		// ctx.close();
	}

	private <T> void insertCollec(Collection<T> collection) {
		for (T element : collection) {
			em.persist(element);
		}
	}

	private void insertPeriode() {
		LocalDate aujourdhui = LocalDate.now();
		LocalDate debutPeriode = LocalDate.of(aujourdhui.getYear(), 1, 1);

		while (debutPeriode.getYear() < aujourdhui.plusYears(1).getYear()) {
			Periode periode = new Periode();
			periode.setDateDebut(debutPeriode);
			periode.setDateFin(debutPeriode.plusMonths(1).minusDays(1));

			em.persist(periode);

			debutPeriode = debutPeriode.plusMonths(1);
		}
	}
}
