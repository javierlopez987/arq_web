package edu.tudai.dao.jpa;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import edu.tudai.dao.i.DAOEstudiante;
import edu.tudai.pojo.Estudiante;

public class JPADAOEstudiante implements DAOEstudiante {
	private EntityManager em;
	
	public JPADAOEstudiante(EntityManager em) {
		this.em = em;
	}
	@Override
	public boolean insertEstudiante(Estudiante e) {
		boolean inserted;
		try {
			em.getTransaction().begin();
			em.persist(e);;
			em.getTransaction().commit();
			inserted = true;
		} catch (Exception exc) {
			System.out.println(exc);
			inserted = false;
		}
		return inserted;
	}

	@Override
	public boolean deleteEstudiante(Estudiante e) {
		boolean deleted;
		em.getTransaction().begin();
		String jpql = "DELETE FROM Persona p WHERE p = ?1";
		
		try {
			Query query = em.createQuery(jpql);
			query.setParameter(1, e);
			query.executeUpdate();
			deleted = true;
		} catch (Exception exc) {
			deleted = false;
		}
		em.getTransaction().commit();
		
		return deleted;
	}

	@Override
	public Estudiante findEstudiante(int id) {
		return em.find(Estudiante.class, id);
	}

	@Override
	public boolean updateEstudiante(Estudiante e) {
		boolean updated;
		Estudiante toBeModified = em.find(Estudiante.class, e.getId_estudiante());
		
		try {
			em.getTransaction().begin();
			toBeModified.setNombre(e.getNombre());
			toBeModified.setEdad(e.getEdad());
			toBeModified.setDni(e.getDni());
			em.getTransaction().commit();
			updated = true;
		} catch (Exception exc) {
			System.out.println(exc);
			updated = false;
		}
		
		return updated;
	}

	@Override
	public Collection<Estudiante> selectEstudiantes() {
		Collection<Estudiante> result = null;
		String jpql = "SELECT e FROM Estudiante e";
		
		em.getTransaction().begin();
		try {
			TypedQuery<Estudiante> query = em.createQuery(jpql, Estudiante.class);
			result = query.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		}
		em.getTransaction().commit();
		
		return result;
	}

}
