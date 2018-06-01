package dev.paie.entite;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Periode {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "DATE_DEBUT", nullable = false)
	private LocalDate dateDebut;

	@Column(name = "DATE_FIN", nullable = false)
	private LocalDate dateFin;

	@OneToMany(mappedBy = "periode")
	private List<BulletinSalaire> listeBulletins;

	public LocalDate getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	public LocalDate getDateFin() {
		return dateFin;
	}

	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date dateDebutToDate() {
		return Date.valueOf(this.dateDebut);
	}

	public Date dateFinToDate() {
		return Date.valueOf(this.dateFin);
	}

	public String toString() {
		String dateDebutFormat = this.dateDebut.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String dateFinFormat = this.dateFin.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return dateDebutFormat + " - " + dateFinFormat;
	}

}
