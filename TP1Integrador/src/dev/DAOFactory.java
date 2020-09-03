package dev;

public abstract class DAOFactory {
	
	public abstract DAOCliente getDAOCliente();
	
	public abstract DAOProducto getDAOProducto();
	
	public abstract DAOFactura getDAOFactura();
	
	public static DAOFactory getDAOFactory() {
		return new MySQLDAOFactory();
	}
}
