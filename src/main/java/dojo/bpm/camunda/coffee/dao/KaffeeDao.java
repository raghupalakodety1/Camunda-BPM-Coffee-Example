package dojo.bpm.camunda.coffee.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import dojo.bpm.camunda.coffee.model.Kaffee;

public class KaffeeDao {

	@PersistenceContext
	EntityManager em;

	public void persist(Kaffee kaffee) {
		if (em.contains(kaffee)) {
			em.merge(kaffee);
		} else {
			em.persist(kaffee);
		}
	}

	public Kaffee find(long id) {
		return em.find(Kaffee.class, id);
	}

	public List<Kaffee> getAllKaffees() {
		TypedQuery<Kaffee> q = em.createQuery("FROM Kaffee ORDER BY name",
				Kaffee.class);
		return q.getResultList();
	}

}
