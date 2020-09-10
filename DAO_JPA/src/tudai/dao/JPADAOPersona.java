package tudai.dao;

import java.util.Collection;

import javax.persistence.EntityManager;

import tudai.dao.model.Persona;

public class JPADAOPersona implements DAOPersona {
	private EntityManager em;
	
	public JPADAOPersona(EntityManager em) {
		this.em = em;
	}
	@Override
	public int insertPersona(Persona p) {
		em.getTransaction().begin();
		em.persist(p);;
		em.getTransaction().commit();
		return 0;
	}

	@Override
	public boolean deletePersona(Persona p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Persona findPersona(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePersona(Persona p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Persona> selectPersonas() {
		em.getTransaction().begin();
		@SuppressWarnings("unchecked")
		Collection<Persona> personas = em.createQuery("SELECT p FROM Persona p").getResultList();
		em.getTransaction().commit();
		return personas;
	}

}
