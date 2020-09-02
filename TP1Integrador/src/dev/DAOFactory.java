package dev;

public abstract class DAOFactory {
	
	public abstract DAOCliente getDAOCliente();
	
	public static DAOFactory getDAOFactory() {
		return new MySQLDAOFactory();
	}
}
