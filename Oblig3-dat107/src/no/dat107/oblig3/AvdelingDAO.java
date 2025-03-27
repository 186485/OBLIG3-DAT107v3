package no.dat107.oblig3;

import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class AvdelingDAO {

    private EntityManagerFactory emf;

    public AvdelingDAO() {
    	emf = Persistence.createEntityManagerFactory("oblig3");
    }

    public Avdeling finnAvdelingMedId(int id) {

        EntityManager em = emf.createEntityManager();

        Avdeling avdeling = null;
        try {
            avdeling = em.find(Avdeling.class, id);
        } finally {
            em.close();
        }
        return avdeling;
    }
}

	

