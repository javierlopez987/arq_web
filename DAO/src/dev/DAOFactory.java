package dev;

public abstract class DAOFactory {
	
	public abstract DAOPersona getDAOPersona();
	
	public static DAOFactory getDAOFactory() {
		return new MySQLDAOFactory();
	}
}
