package dev;

import java.sql.Connection;

public class MySQLDAOFactory extends DAOFactory {

	public static Connection createConnection() {
		return MySQLDB.getInstance().getConnection();
	}
	
	@Override
	public DAOCliente getDAOCliente() {
		return new MySQLDAOCliente();
	}
	
	@Override
	public DAOProducto getDAOProducto() {
		return new MySQLDAOProducto();
	}

	@Override
	public DAOFactura getDAOFactura() {
		return new MySQLDAOFactura();
	}
	
	@Override
	public DAOManager getDAOManager() {
		return new MySQLDAOManager();
	}

}
