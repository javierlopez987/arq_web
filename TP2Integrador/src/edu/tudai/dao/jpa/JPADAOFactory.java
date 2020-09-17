package edu.tudai.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.tudai.dao.i.DAOFactory;
import edu.tudai.dao.i.DAOEstudiante;
import edu.tudai.dao.i.DAOCarrera;

public class JPADAOFactory extends DAOFactory{
	private EntityManagerFactory emf;
	private EntityManager em;
	public JPADAOFactory() {
		emf = Persistence.createEntityManagerFactory("ArqWebTP2");
		em = emf.createEntityManager();
	}
	
	@Override
	public DAOEstudiante getDAOEstudiante() {
		return new JPADAOEstudiante(em);
	}

	@Override
	public DAOCarrera getDAOCarrera() {
		return new JPADAOCarrera(em);
	}

}
