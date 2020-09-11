package tudai.dao;

import java.sql.Connection;

public class MySQLDAOFactory extends DAOFactory {

	public static Connection createConnection() {
		return MySQLDB.getInstance().getConnection();
	}
	
	@Override
	public DAOPersona getDAOPersona() {
		return new MySQLDAOPersona();
	}

	@Override
	public DAOTurno getDAOTurno() {
		// TODO Auto-generated method stub
		return null;
	}

}
