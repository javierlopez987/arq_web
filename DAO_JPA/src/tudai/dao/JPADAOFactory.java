package tudai.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
