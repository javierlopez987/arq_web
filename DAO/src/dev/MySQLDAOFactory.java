package dev;

import java.sql.Connection;

public class MySQLDAOFactory extends DAOFactory {

	public static Connection createConnection() {
		return MySQLDB.getInstance().getConnection();
	}
	
	@Override
	public DAOPersona getDAOPersona() {
		return new MySQLDAOPersona();
	}

}
