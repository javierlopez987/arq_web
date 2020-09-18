package edu.tudai.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import edu.tudai.dao.i.DAOMatricula;
import edu.tudai.pojo.Carrera;
import edu.tudai.pojo.Estudiante;
import edu.tudai.pojo.Matricula;

public class JPADAOMatricula implements DAOMatricula{

	private EntityManager em;

	public JPADAOMatricula(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public boolean insertMatricula(Matricula m){
		boolean inserted;
		try {
			em.getTransaction().begin();
			em.persist(m);;
			em.getTransaction().commit();
			inserted = true;
		} catch (Exception exc) {
			System.out.println(exc);
			inserted = false;
		}
		return inserted;
	}
	
	public boolean deleteMatricula(Matricula m){
		boolean deleted;
		em.getTransaction().begin();
		String jpql = "DELETE FROM Matricula m WHERE m = ?1";
		
		try {
			Query query = em.createQuery(jpql);
			query.setParameter(1, m);
			query.executeUpdate();
			deleted = true;
		} catch (Exception exc) {
			deleted = false;
		}
		em.getTransaction().commit();
		
		return deleted;
	}
	
	public Matricula findMatricula(int id){
		return em.find(Matricula.class, id);
	}
	
	public boolean updateMatricula(Matricula m) {
		return false;
	}
	
	public Collection<Matricula> selectMatriculas(){
		Collection<Matricula> result;
		String jpql = "SELECT t FROM Matricula m";
		
		em.getTransaction().begin();
		try {
			TypedQuery<Matricula> query = em.createQuery(jpql, Matricula.class);
			result = query.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			result = null;
		}
		em.getTransaction().commit();
		
		return result;
	}
	
	public Collection<Matricula> selectMatriculas(Estudiante p){
		Collection<Matricula> result;
		String jpql = "SELECT m FROM Matricula m WHERE m.estudiantes = ?1";
		
		em.getTransaction().begin();
		try {
			TypedQuery<Matricula> query = em.createQuery(jpql, Matricula.class);
			query.setParameter(1, p);
			result = query.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			result = null;
		}
		em.getTransaction().commit();
		
		return result;
	}
	
	public Collection<Matricula> selectMatriculas(Carrera c){
		Collection<Matricula> result;
		String jpql = "SELECT m FROM Matricula m WHERE m.carrera = ?1";
		
		em.getTransaction().begin();
		try {
			TypedQuery<Matricula> query = em.createQuery(jpql, Matricula.class);
			query.setParameter(1, c);
			result = query.getResultList();
		} catch (Exception e) {
			System.out.println(e);
			result = null;
		}
		em.getTransaction().commit();
		
		return result;
	}
	
}
