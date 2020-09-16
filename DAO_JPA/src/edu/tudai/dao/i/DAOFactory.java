package edu.tudai.dao.i;

import edu.tudai.dao.jpa.JPADAOFactory;
import edu.tudai.dao.mysql.MySQLDAOFactory;

/**
 * Clase abstracta DAOFactory
 * @author Javier
 *
 */
public abstract class DAOFactory {
	// Lista de tipos de DAO que soporta el factory
	public static final int MYSQL = 1;
	public static final int JPA = 2;

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
	
	// Debe haber un método por cada DAO que puede ser creado
	public abstract DAOPersona getDAOPersona();
	
	public abstract DAOTurno getDAOTurno();
	
}
