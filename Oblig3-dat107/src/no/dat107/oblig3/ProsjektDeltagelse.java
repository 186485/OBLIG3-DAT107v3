package no.dat107.oblig3;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "oblig3")
public class ProsjektDeltagelse {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int deltagelseid;

	private int timer;

	private String rolle;
	@ManyToOne
	@JoinColumn(name = "ansattid")
	private Ansatt ansatt;

	@ManyToOne
	@JoinColumn(name = "prosjektid")
	private Prosjekt prosjekt;

	public ProsjektDeltagelse() {
	}

	public ProsjektDeltagelse(Ansatt ansatt, Prosjekt prosjekt, int timer, String rolle) {
		this.ansatt = ansatt;
		this.prosjekt = prosjekt;
		this.timer = timer;
		this.rolle = rolle;
		// Hvis vi gjør dette her slipper vi å gjøre det i DAO
		ansatt.leggTilProsjektDeltagelse(this);
		prosjekt.leggTilProsjektDeltagelse(this);
		;
	}

	public void skrivUt(String innrykk) {
		System.out.printf("%sDeltagelse: %s %s, %s, %d timer", innrykk, ansatt.getNavn(), ansatt.getEtternavn(),
				prosjekt.getNavn(), timer, rolle);
	}

	public void setTimer(int timer) {
		this.timer = timer;
	}

	public void setAnsatt(Ansatt ansatt) {
		this.ansatt = ansatt;
	}

	public void setProsjekt(Prosjekt prosjekt) {
		this.prosjekt = prosjekt;
	}

	public String getRolle() {
		return rolle;
	}

	public void setRolle(String rolle) {
		this.rolle = rolle;
	}
	
    @Override
    public String toString() {
        return "ProsjektDeltagelse{id=" + deltagelseid + 
               ", timer=" + timer + 
               ", ansatt=" + ansatt.getNavn() + " " + ansatt.getEtternavn() + 
               ", prosjekt=" + prosjekt.getNavn() + '}';
    }

}
