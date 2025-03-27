package no.dat107.oblig3;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "avdeling", schema = "oblig3")
public class Avdeling {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String navn;

    @OneToMany(mappedBy = "avdeling", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Ansatt> ansatte;
    
    public Avdeling() {}

    public Avdeling(String navn) {
        this.navn = navn;
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

    public List<Ansatt> getAnsatte() {
        return ansatte;
    }

    public void skrivUtMedAnsatte() {
        System.out.println("\nAvdeling: " + navn);
        if (ansatte != null) {
            for (Ansatt a : ansatte) {
                System.out.println(" - " + a.getNavn());
            }
        }
    }
}