package no.dat107.oblig3;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class ProsjektDeltagelseDAO {

	private EntityManagerFactory emf;

	public ProsjektDeltagelseDAO() {
		emf = Persistence.createEntityManagerFactory("oblig3");
	}

	
	public void lagreProsjektDeltagelse(ProsjektDeltagelse deltagelse) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(deltagelse);
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


	public ProsjektDeltagelse finnProsjektDeltagelseMedId(int id) {
		EntityManager em = emf.createEntityManager();

		ProsjektDeltagelse deltagelse = null;
		try {
			deltagelse = em.find(ProsjektDeltagelse.class, id);
		} finally {
			em.close();
		}
		return deltagelse;
	}

	
	public List<ProsjektDeltagelse> finnAlleDeltagelserForProsjekt(int prosjektId) {
		EntityManager em = emf.createEntityManager();

		List<ProsjektDeltagelse> deltagelser = null;
		try {
			String query = "SELECT p FROM ProsjektDeltagelse p WHERE p.prosjekt.id = :prosjektId";
			deltagelser = em.createQuery(query, ProsjektDeltagelse.class).setParameter("prosjektId", prosjektId)
					.getResultList();
		} finally {
			em.close();
		}
		return deltagelser;
	}

	public ProsjektDeltagelse finnProsjektDeltagelse(Ansatt ansatt, Prosjekt prosjekt) {
		EntityManager em = emf.createEntityManager();
		try {

			String query = "SELECT p FROM ProsjektDeltagelse p WHERE p.ansatt = :ansatt AND p.prosjekt = :prosjekt";
			return em.createQuery(query, ProsjektDeltagelse.class).setParameter("ansatt", ansatt)
					.setParameter("prosjekt", prosjekt).getSingleResult();

		} finally {
			em.close();
		}
	}

	public void oppdaterProsjektDeltagelse(ProsjektDeltagelse prosjektDeltagelse) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.merge(prosjektDeltagelse);
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