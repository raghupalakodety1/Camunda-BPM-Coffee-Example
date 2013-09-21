package dojo.bpm.camunda.coffee.dao;

import dojo.bpm.camunda.coffee.model.Bestellung;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

public class BestellungDao {

	@PersistenceContext
	EntityManager em;

    public void persist(Bestellung bestellung) {
        try {
            em.getTransaction().begin();
            if (em.contains(bestellung)) {
                em.merge(bestellung);
            } else {
                em.persist(bestellung);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Bestellung find(long id) {
        try {
            em.getTransaction().begin();
            Bestellung bestellung = em.find(Bestellung.class, id);
            em.getTransaction().commit();
            return bestellung;
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public List<Bestellung> getAllBestellungen() {
        try {
            em.getTransaction().begin();
            TypedQuery<Bestellung> q = em.createQuery(
				"FROM Bestellung ORDER BY bestelldatum DESC", Bestellung.class);
            List<Bestellung> list = q.getResultList();
            em.getTransaction().commit();
            return list;
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

}
