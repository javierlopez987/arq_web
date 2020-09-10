package tudai.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPADAOFactory extends DAOFactory{
	private EntityManagerFactory emf;
	private EntityManager em;
	public JPADAOFactory() {
		emf = Persistence.createEntityManagerFactory("DAOJPADemo");
	}
	
	@Override
	public DAOPersona getDAOPersona() {
		em = emf.createEntityManager();
		return new JPADAOPersona(em);
	}

}
