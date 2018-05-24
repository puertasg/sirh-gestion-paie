package dev.paie.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PaieUtilsTest {
	private PaieUtils paieUtils;

	@Before
	public void onSetup() {
		// code exécuté avant chaque test
	}

	@Test
	public void test_formaterBigDecimal_entier_positif() {

		String resultat = paieUtils.formaterBigDecimal(new BigDecimal("2"));
		assertThat(resultat, equalTo("2.00"));
	}

	@Test
	public void test_formaterBigDecimal_trois_chiffres_apres_la_virgule() {

		String resultat = paieUtils.formaterBigDecimal(new BigDecimal("2.199"));
		assertThat(resultat, equalTo("2.20"));
	}

	@After
	public void onExit() {
		// code exécuté après chaque test
	}
}