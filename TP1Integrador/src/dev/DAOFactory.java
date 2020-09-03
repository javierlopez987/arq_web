package dev;

public abstract class DAOFactory {
	
	public abstract DAOCliente getDAOCliente();
	
	public abstract DAOProducto getDAOProducto();
	
	public static DAOFactory getDAOFactory() {
		return new MySQLDAOFactory();
	}
}
