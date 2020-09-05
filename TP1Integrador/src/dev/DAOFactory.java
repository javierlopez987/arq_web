package dev;

public abstract class DAOFactory {
	
	public abstract DAOCliente getDAOCliente();
	
	public abstract DAOProducto getDAOProducto();
	
	public abstract DAOFactura getDAOFactura();
	
	public abstract DAOManager getDAOManager();

	
	public static DAOFactory getDAOFactory() {
		return new MySQLDAOFactory();
	}
}
