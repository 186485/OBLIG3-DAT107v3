package no.dat107.oblig3;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;


import java.util.ArrayList;
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
                avdeling.getAnsatte().size(); 
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
    
    public boolean erAnsattSjef(int ansattId) {
        EntityManager em = emf.createEntityManager();
        boolean erSjef = false;

        try {
        	String queryString = "SELECT COUNT(a) FROM Avdeling a WHERE a.sjef = :ansattId";
            TypedQuery<Long> query = em.createQuery(queryString, Long.class);
            query.setParameter("ansattId", ansattId);
            long antall = query.getSingleResult();
            
            erSjef = antall > 0; //antall > 0, betyr at ansatt er sjef
        } finally {
            em.close();
        }

        return erSjef;
    }
    
    public void leggTilNyAvdelingMedSjef(String avdelingsNavn, int sjefId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            
            
            Ansatt sjef = em.find(Ansatt.class, sjefId);
            if (sjef == null) {
                throw new IllegalArgumentException("Fant ikke ansatt med ID: " + sjefId);
            }
           
            Avdeling nyAvdeling = new Avdeling();
            nyAvdeling.setNavn(avdelingsNavn);
            nyAvdeling.setSjef(sjef.getId());

            em.persist(nyAvdeling);

            
            sjef.setAvdeling(nyAvdeling);
            em.merge(sjef);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
    
}
