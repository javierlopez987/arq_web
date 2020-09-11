package tudai.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tudai.orm.Persona;

public class JPADAOPersona implements DAOPersona {
	private EntityManager em;
	
	public JPADAOPersona(EntityManager em) {
		this.em = em;
	}
	@Override
	public boolean insertPersona(Persona p) {
		boolean inserted;
		try {
			em.getTransaction().begin();
			em.persist(p);;
			em.getTransaction().commit();
			inserted = true;
		} catch (Exception e) {
			System.out.println(e);
			inserted = false;
		}
		return inserted;
	}

	@Override
	public boolean deletePersona(Persona p) {
		boolean deleted;
		em.getTransaction().begin();
		String jpql = "DELETE FROM Persona p WHERE p = ?1";
		
		try {
			Query query = em.createQuery(jpql);
			query.setParameter(1, p);
			query.executeUpdate();
			deleted = true;
		} catch (Exception e) {
			deleted = false;
		}
		em.getTransaction().commit();
		
		return deleted;
	}

	@Override
	public Persona findPersona(int id) {
		return em.find(Persona.class, id);
	}

	@Override
	public boolean updatePersona(Persona p) {
		boolean updated;
		Persona toBeModified = em.find(Persona.class, p.getId());
		
		try {
			em.getTransaction().begin();
			toBeModified.setNombre(p.getNombre());
			toBeModified.setEdad(p.getEdad());
			toBeModified.setDni(p.getDni());
			toBeModified.setDomicilio(p.getDomicilio());
			em.getTransaction().commit();
			updated = true;
		} catch (Exception e) {
			System.out.println(e);
			updated = false;
		}
		
		return updated;
	}

	@Override
	public Collection<Persona> selectPersonas() {
		Collection<Persona> result = null;
		String jpql = "SELECT p FROM Persona p";
		
		em.getTransaction().begin();
		try {
			TypedQuery<Persona> query = em.createQuery(jpql, Persona.class);
			result = query.getResultList();
		} catch (Exception e) {
			System.out.println(e);
		}
		em.getTransaction().commit();
		
		return result;
	}

}
