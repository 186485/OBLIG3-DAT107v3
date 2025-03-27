package no.dat107.oblig3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

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
            if (avdeling != null) {
                avdeling.getAnsatte().size(); // Laster ansatte (hvis Lazy)
            }
        } finally {
            em.close();
        }
        return avdeling;
    }

    public List<Avdeling> finnAlleAvdelinger() {
        EntityManager em = emf.createEntityManager();
        List<Avdeling> avdelinger = null;
        try {
            avdelinger = em.createQuery("FROM Avdeling", Avdeling.class).getResultList();
        } finally {
            em.close();
        }
        return avdelinger;
    }

    public void leggTilAvdeling(Avdeling avdeling) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(avdeling);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
