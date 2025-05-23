package no.dat107.oblig3;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "oblig3")
public class Prosjekt {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String navn;
	private String beskrivelse;

	@OneToMany(mappedBy = "prosjekt")
	private List<ProsjektDeltagelse> deltagelser;

	public void skrivUt(String innrykk) {
		System.out.printf("%sProsjekt nr %d: %s", innrykk, id, navn);
	}

	public void skrivUtMedAnsatte() {
		System.out.println();
		skrivUt("");
		deltagelser.forEach(a -> a.skrivUt("\n   "));
	}

	public void leggTilProsjektDeltagelse(ProsjektDeltagelse prosjektdeltagelse) {
		deltagelser.add(prosjektdeltagelse);
	}

	public void fjernProsjektDeltagelse(ProsjektDeltagelse prosjektdeltagelse) {
		deltagelser.remove(prosjektdeltagelse);
	}

	public int getId() {
		return id;
	}

	public String getNavn() {
		return navn;
	}

	public List<ProsjektDeltagelse> getDeltagelser() {
		return deltagelser;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}

	public String getBeskrivelse() {
		return beskrivelse;
	}

	public void setBeskrivelse(String beskrivelse) {
		this.beskrivelse = beskrivelse;
	}

}