package edu.tudai.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.tudai.dao.i.DAOFactory;
import edu.tudai.dao.i.DAOPersona;
import edu.tudai.dao.i.DAOTurno;

public class JPADAOFactory extends DAOFactory{
	private EntityManagerFactory emf;
	private EntityManager em;
	public JPADAOFactory() {
		emf = Persistence.createEntityManagerFactory("DAOJPADemo");
		em = emf.createEntityManager();
	}
	
	@Override
	public DAOPersona getDAOPersona() {
		return new JPADAOPersona(em);
	}

	@Override
	public DAOTurno getDAOTurno() {
		return new JPADAOTurno(em);
	}

}
