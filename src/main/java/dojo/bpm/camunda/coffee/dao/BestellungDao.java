package dojo.bpm.camunda.coffee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dojo.bpm.camunda.coffee.model.Bestellung;

public class BestellungDao {

	@PersistenceContext
	EntityManager em;

	public void persist(Bestellung bestellung) {
		if (em.contains(bestellung)) {
			em.merge(bestellung);
		} else {
			em.persist(bestellung);
		}
	}

	public Bestellung find(long id) {
		return em.find(Bestellung.class, id);
	}

	public List<Bestellung> getAllBestellungen() {
		TypedQuery<Bestellung> q = em.createQuery(
				"FROM Bestellung ORDER BY bestelldatum DESC", Bestellung.class);
		return q.getResultList();
	}

}
