package edu.tudai.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import edu.tudai.dao.i.DAOTurno;
import edu.tudai.pojo.Persona;
import edu.tudai.pojo.Turno;

public class JPADAOTurno implements DAOTurno {
	private EntityManager em;
	
	public JPADAOTurno(EntityManager em) {
		this.em = em;
	}

	@Override
	public boolean insertTurno(Turno t) {
		boolean inserted;
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
			inserted = true;
		} catch (Exception e) {
			System.out.println(e);
			inserted = false;
		}
		return inserted;
	}

	@Override
	public boolean deleteTurno(Turno t) {
		boolean deleted;
		em.getTransaction().begin();
		String jpql = "DELETE FROM Turno t WHERE t = ?1";
		
		try {
			Query query = em.createQuery(jpql);
			query.setParameter(1, t);
			query.executeUpdate();
			deleted = true;
		} catch (Exception e) {
			deleted = false;
		}
		em.getTransaction().commit();
		
		return deleted;
	}

	@Override
	public Turno findTurno(int id) {
		return em.find(Turno.class, id);
	}

	@Override
	public boolean updateTurno(Turno t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Turno> selectTurnos() {
		Collection<Turno> result;
		String jpql = "SELECT t FROM Turno t";
		
		em.getTransaction().begin();
		try {
			TypedQuery<Turno> query = em.createQuery(jpql, Turno.class);
			result = query.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			result = null;
		}
		em.getTransaction().commit();
		
		return result;
	}

	@Override
	public Collection<Turno> selectTurnos(Persona p) {
		Collection<Turno> result;
		String jpql = "SELECT t FROM Turno t WHERE t.personas = ?1";
		
		em.getTransaction().begin();
		try {
			TypedQuery<Turno> query = em.createQuery(jpql, Turno.class);
			query.setParameter(1, p);
			result = query.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			result = null;
		}
		em.getTransaction().commit();
		
		return result;
	}
}
