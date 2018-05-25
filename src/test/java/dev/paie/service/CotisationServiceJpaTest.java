package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.H2Config;
import dev.paie.config.JpaConfig;
import dev.paie.entite.Cotisation;

@Configuration
@ImportResource("jdd-config.xml")
@ContextConfiguration(classes = {CotisationServiceJpa.class, JpaConfig.class, H2Config.class})
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {
	
	@Autowired
	private Cotisation cotisation;
	
	@Autowired
	private CotisationService cotisationService;
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour()
	{
		// TODO sauvegarder une nouvelle cotisation
		cotisationService.sauvegarder(cotisation);
        // TODO vérifier qu'il est possible de récupérer la nouvelle cotisation via la méthode lister
		List<Cotisation> listCotisations = cotisationService.lister();
		assertThat(listCotisations.contains(cotisation));

        // TODO modifier une cotisation
		cotisation.setLibelle("Libelle mis a jour");
		cotisationService.mettreAJour(cotisation);

        // TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		List<Cotisation> listCotisationUpdate = cotisationService.lister();
		assertThat(listCotisationUpdate.contains(cotisation));
	}
}
