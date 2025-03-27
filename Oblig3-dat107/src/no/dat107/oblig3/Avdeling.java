package no.dat107.oblig3;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(schema = "oblig3")
public class Avdeling {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String navn;
    
    @ManyToMany
    @JoinTable(
            name = "oblig3.ansattdeltagelse", // NB! Må ha med schema !!!
            joinColumns = @JoinColumn(name="avdelingid"),
            inverseJoinColumns = @JoinColumn(name="ansattid"))
    private List<Ansatt> ansatte;
    
    public int getId() {
		return id;
	}

    public void skrivUt(String innrykk) {
        System.out.printf("%sProsjekt nr %d: %s", innrykk, id, navn);
    }
    
    public void skrivUtMedAnsatte() {
        System.out.println();
        skrivUt("");
        ansatte.forEach(a -> a.toString());
    }

    public void leggTilAnsatt(Ansatt a) {
        ansatte.add(a);
    }

    public void fjernAnsatt(Ansatt a) {
        ansatte.remove(a);
    }

	public String getNavn() {
		return navn;
	}

	public void setNavn(String navn) {
		this.navn = navn;
	}
    
}

