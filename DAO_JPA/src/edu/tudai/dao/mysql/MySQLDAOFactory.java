package edu.tudai.dao.mysql;

import java.sql.Connection;

import edu.tudai.dao.i.DAOFactory;
import edu.tudai.dao.i.DAOPersona;
import edu.tudai.dao.i.DAOTurno;

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
