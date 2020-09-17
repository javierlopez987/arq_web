package edu.tudai.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import edu.tudai.dao.i.DAOCarrera;
import edu.tudai.pojo.*;

public class JPADAOCarrera implements DAOCarrera {
	private EntityManager em;
	
	public JPADAOCarrera(EntityManager em) {
		this.em = em;
	}

	@Override
	public boolean insertCarrera(Carrera c) {
		boolean inserted;
		try {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();
			inserted = true;
		} catch (Exception e) {
			System.out.println(e);
			inserted = false;
		}
		return inserted;
	}

	@Override
	public boolean deleteCarrera(Carrera c) {
		boolean deleted;
		em.getTransaction().begin();
		String jpql = "DELETE FROM Carrera c WHERE c = ?1";
		
		try {
			Query query = em.createQuery(jpql);
			query.setParameter(1, c);
			query.executeUpdate();
			deleted = true;
		} catch (Exception e) {
			deleted = false;
		}
		em.getTransaction().commit();
		
		return deleted;
	}

	@Override
	public Carrera findCarrera(int id) {
		return em.find(Carrera.class, id);
	}

	@Override
	public boolean updateCarrera(Carrera t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Carrera> selectCarreras() {
		Collection<Carrera> result;
		String jpql = "SELECT t FROM Carrera t";
		
		em.getTransaction().begin();
		try {
			TypedQuery<Carrera> query = em.createQuery(jpql, Carrera.class);
			result = query.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			result = null;
		}
		em.getTransaction().commit();
		
		return result;
	}

	@Override
	public Collection<Carrera> selectCarreras(Estudiante p) {
		Collection<Carrera> result;
		String jpql = "SELECT c FROM Carrera c WHERE c.estudiantes = ?1";
		
		em.getTransaction().begin();
		try {
			TypedQuery<Carrera> query = em.createQuery(jpql, Carrera.class);
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
