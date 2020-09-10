package tudai.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Clase abstracta DAOFactory
 * @author Win10
 *
 */
public abstract class DAOFactory {
	// Lista de tipos de DAO que soporta el factory
	public static final int MYSQL = 1;
	public static final int JPA = 2;

	// Debe haber un método por cada DAO que puede ser creado
	public abstract DAOPersona getDAOPersona();
	
	public static DAOFactory getDAOFactory(int whichFactory) {
		DAOFactory daoFactory = null;
		
		switch (whichFactory) {
		case MYSQL:
			daoFactory = new MySQLDAOFactory();
			break;
		case JPA:
			daoFactory = new JPADAOFactory();
			break;
		default:
			daoFactory = null;
			break;
		}
		return daoFactory;
	}
}
