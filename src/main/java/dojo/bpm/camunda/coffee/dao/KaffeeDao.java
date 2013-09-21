package dojo.bpm.camunda.coffee.dao;

import dojo.bpm.camunda.coffee.model.Kaffee;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class KaffeeDao {
    EntityManager em;
	public KaffeeDao(EntityManager em) {
		this.em=em;
	}

    public void persist(Kaffee kaffee) {
        try {
            em.getTransaction().begin();
            if (em.contains(kaffee)) {
                em.merge(kaffee);
            } else {
                em.persist(kaffee);
            }
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public Kaffee find(long id) {
        try {
            em.getTransaction().begin();
            Kaffee kaffee = em.find(Kaffee.class, id);
            em.getTransaction().commit();
            return kaffee;
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    public List<Kaffee> getAllKaffees() {
        try {
            em.getTransaction().begin();
            TypedQuery<Kaffee> q = em.createQuery("FROM Kaffee ORDER BY name",
                    Kaffee.class);
            List<Kaffee> list = q.getResultList();
            em.getTransaction().commit();
            return list;
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

}
