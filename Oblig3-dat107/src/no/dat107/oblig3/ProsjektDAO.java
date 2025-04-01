package no.dat107.oblig3;

import java.util.Map;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProsjektDAO {

	private EntityManagerFactory emf;

	public ProsjektDAO() {
		emf = Persistence.createEntityManagerFactory("oblig3");
	}

	public Prosjekt finnProsjektMedId(int id) {

		EntityManager em = emf.createEntityManager();

		Prosjekt prosjekt = null;
		try {
			prosjekt = em.find(Prosjekt.class, id);
		} finally {
			em.close();
		}
		return prosjekt;
	}

	public void opprettProsjekt(String prosjektNavn, String beskrivelse) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();

			
			Prosjekt nyttProsjekt = new Prosjekt();
			nyttProsjekt.setNavn(prosjektNavn);
			nyttProsjekt.setBeskrivelse(beskrivelse);

			em.persist(nyttProsjekt);

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