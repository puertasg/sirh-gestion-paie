package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import dev.paie.entite.Grade;

public class GradeServiceJdbcTemplateTest {
	
	@Autowired
	private GradeService gradeService;
	
	@Test
	public void test_sauvegarder_liste_mettre_a_jour()
	{
		Grade grade = new Grade();
		grade.setId(123);
		grade.setCode("abc");
		grade.setNbHeuresBase(new BigDecimal("123.456"));
		grade.setNbHeuresBase(new BigDecimal("78.9"));
		
		Grade nouveauGrade = new Grade();
		nouveauGrade.setId(123);
		nouveauGrade.setCode("abc");
		nouveauGrade.setNbHeuresBase(new BigDecimal("123.456"));
		nouveauGrade.setNbHeuresBase(new BigDecimal("78.9"));
		
		// TODO sauvegarder un nouveau grade
		try
		{
			gradeService.mettreAJour(grade);
		}
		catch(DataAccessException ex)
		{
			fail();
		}

        // TODO vérifier qu'il est possible de récupérer le nouveau grade via la méthode lister
		try
		{
			List<Grade> listeGrade = gradeService.lister();
			assertThat(listeGrade.contains(grade));
		}
		catch(DataAccessException ex)
		{
			fail();
		}
		
        // TODO modifier un grade
		try
		{
			gradeService.mettreAJour(nouveauGrade);
		}
		catch(DataAccessException ex)
		{
			fail();
		}

        // TODO vérifier que les modifications sont bien prises en compte via la méthode lister
		try
		{
			List<Grade> listeGradeUpdate = gradeService.lister();
			assertThat(listeGradeUpdate.contains(nouveauGrade));
		}
		catch(DataAccessException ex)
		{
			fail();
		}
	}
}
