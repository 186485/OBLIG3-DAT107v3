package no.dat107.oblig3;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "oblig3")
public class Ansatt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Automatisk ID-generering
	private Integer id;
	private String navn;
	private String brukernavn;
	private String etternavn;
	private LocalDate ansettelse; // Ansettelsesdato
	private String stilling;
	private Double monedslonn; // Månedslønn

	@ManyToOne
	@JoinColumn(name = "avdeling_id", nullable = false)
	private Avdeling avdeling;

	@OneToMany(mappedBy = "ansatt")
	private List<ProsjektDeltagelse> deltagelser;

	// Standard konstruktør
	public Ansatt() {
	}

	// Konstruktør for å sette alle feltene
	public Ansatt(String navn, String brukernavn, String etternavn, LocalDate ansettelse, String stilling,
			Double monedslonn, Avdeling avdeling) {
		this.navn = navn;
		this.brukernavn = brukernavn;
		this.etternavn = etternavn;
		this.ansettelse = ansettelse;
		this.stilling = stilling;
		this.monedslonn = monedslonn;
		this.avdeling = avdeling;
	}

	@Override
	public String toString() {
		String utskrift = String.format(
				"Ansatt (id=%d, navn=%s, brukernavn=%s, etternavn=%s, ansettelse=%s, stilling=%s, monedslonn=%.2f, avdeling=%s ) \n",
				id, navn, brukernavn, etternavn, ansettelse, stilling, monedslonn, avdeling.getNavn());

		return utskrift;
	}

	public int getId() {
		return id;
	}

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getBrukernavn() {
		return brukernavn;
	}

	public void setBrukernavn(String brukernavn) {
		this.brukernavn = brukernavn;
	}

	public String getEtternavn() {
		return etternavn;
	}

	public void setEtternavn(String etternavn) {
		this.etternavn = etternavn;
	}

	public LocalDate getAnsettelse() {
		return ansettelse;
	}

	public void setAnsettelse(LocalDate ansettelse) {
		this.ansettelse = ansettelse;
	}

	public String getStilling() {
		return stilling;
	}

	public void setStilling(String stilling) {
		this.stilling = stilling;
	}

	public Double getMonedslonn() {
		return monedslonn;
	}

	public void setMonedslonn(Double monedslonn) {
		this.monedslonn = monedslonn;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Avdeling getAvdeling() {
		return avdeling;
	}

	public void setAvdeling(Avdeling avdeling) {
		this.avdeling = avdeling;
	}

	public void leggTilProsjektDeltagelse(ProsjektDeltagelse prosjektdeltagelse) {
		deltagelser.add(prosjektdeltagelse);
	}

	public void fjernProsjektDeltagelse(ProsjektDeltagelse prosjektdeltagelse) {
		deltagelser.remove(prosjektdeltagelse);
	}

	public List<ProsjektDeltagelse> getDeltagelser() {
		return deltagelser;
	}

}