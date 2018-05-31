package dev.paie.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.Utilisateur;
import dev.paie.entite.Utilisateur.ROLES;

@Service
@Transactional
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void initialiser() {

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

			Utilisateur utilisateurNormal = new Utilisateur();
			utilisateurNormal.setNomUtilisateur("normal");
			utilisateurNormal.setMotDePasse(passwordEncoder.encode("password"));
			utilisateurNormal.setEstActif(true);
			utilisateurNormal.setRole(ROLES.ROLE_UTILISATEUR);
			em.persist(utilisateurNormal);

			Utilisateur utilisateurAdmin = new Utilisateur();
			utilisateurAdmin.setNomUtilisateur("administrateur");
			utilisateurAdmin.setMotDePasse(passwordEncoder.encode("password"));
			utilisateurAdmin.setEstActif(true);
			utilisateurAdmin.setRole(ROLES.ROLE_ADMINISTRATEUR);
			em.persist(utilisateurAdmin);
		}

		// Désormais gérer par try() => Concept Try-with-resources de Java 7
		// ctx.close();
	}
}
