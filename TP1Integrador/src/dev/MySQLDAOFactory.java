package dev;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
	public void close() throws IOException {
		try {
			MySQLDB.getInstance().getConnection().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
