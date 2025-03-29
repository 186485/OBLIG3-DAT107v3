package no.dat107.oblig3;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;



public class AnsattDAO {

    private EntityManagerFactory emf;
    
    public AnsattDAO() {
        emf = Persistence.createEntityManagerFactory("oblig3");
    }
    
    public int saveAnsatt(Ansatt a) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(a);  // Lagrer en ny ansatt i databasen.
            tx.commit();
        
        } catch (Throwable e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
        }
        return a.getId();
    }
    
    public Ansatt retrieveAnsatt(int id) {
        EntityManager em = emf.createEntityManager();

        Ansatt a = null;
        try {
            a = em.find(Ansatt.class, id);
        } finally {
            em.close();
        }
        
        return a;
    }
    
    public List<Ansatt> retrieveAlleAnsatte() {
        EntityManager em = emf.createEntityManager();

        List<Ansatt> ansatte = null;
        try {
            String queryString = "select a from Ansatt a order by a.id";
            TypedQuery<Ansatt> query = em.createQuery(queryString, Ansatt.class);
            ansatte = query.getResultList();
            
        } finally {
            em.close();
        }
        return ansatte;
    }
    
    public void updateAnsatt(Ansatt a) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Ansatt q = em.merge(a);
            em.getTransaction().commit();
        
        } catch (Throwable e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public void deleteAnsatt(Ansatt a) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Ansatt q = em.find(Ansatt.class, a.getId());
            em.remove(q);
            em.getTransaction().commit();
        
        } catch (Throwable e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
    
    public Ansatt finnAnsattMedId(int id) {

        EntityManager em = emf.createEntityManager();

        Ansatt ansatt = null;
        try {
            ansatt = em.find(Ansatt.class, id);
        } finally {
            em.close();
        }
        return ansatt;
    }
    
    public List<Ansatt> finnAnsatteMedBrukernavn(String brukernavn) {
        EntityManager em = emf.createEntityManager();
        List<Ansatt> ansatte = new ArrayList<>();

        try {
            ansatte = em.createQuery("SELECT a FROM Ansatt a WHERE a.brukernavn = :brukernavn", Ansatt.class)
                        .setParameter("brukernavn", brukernavn)
                        .getResultList();
        } finally {
            em.close();
        }

        return ansatte;
    }
    public List<Ansatt> finnAnsattIAvdeling(int avdeling_id) {
        EntityManager em = emf.createEntityManager();
        List<Ansatt> ansatteAvd = new ArrayList<>();

        try {
            ansatteAvd = em.createQuery(
                "SELECT a FROM Ansatt a WHERE a.avdeling.id = :avdeling_id", Ansatt.class)
                .setParameter("avdeling_id", avdeling_id)
                .getResultList();
        } finally {
            em.close();
        }

        return ansatteAvd;
    }
    
    
    public Ansatt finnSjefForAvdeling(int avdelingId) {
        EntityManager em = emf.createEntityManager();

        Ansatt sjef = null;
        try {
            
            String queryString = "SELECT a FROM Ansatt a WHERE a.avdeling.id = :avdelingId AND a.id = a.avdeling.sjef.id";
            TypedQuery<Ansatt> query = em.createQuery(queryString, Ansatt.class);
            query.setParameter("avdelingId", avdelingId);
            sjef = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Ingen sjef funnet for avdelingen.");
        } finally {
            em.close();
        }
        
        return sjef;
    }
    
    public void oppdaterAvdeling(int ansattId, int nyAvdId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Ansatt ansatt = em.find(Ansatt.class, ansattId);
            if (ansatt != null) {
                Avdeling nyAvdeling = em.find(Avdeling.class, nyAvdId);
                if (nyAvdeling != null) {
                    ansatt.setAvdeling(nyAvdeling); 
                    em.merge(ansatt); 
                    em.getTransaction().commit();
                }
            }
        } finally {
            em.close();
        }
    }
//    
}
