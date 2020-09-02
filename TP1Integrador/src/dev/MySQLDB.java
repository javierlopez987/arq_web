package dev;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDB {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URI = "jdbc:mysql://localhost:3306/";
	public static final String SCHEMA = "db_arq_web_test";
	private Connection conn;
	private static MySQLDB instance = new MySQLDB();

	private MySQLDB() {
		try {
			Class.forName(DRIVER).getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		try {
			conn = DriverManager.getConnection(URI, "root", "");
			
			conn.setAutoCommit(false);
			
			createDB();
			
			conn.setCatalog(SCHEMA);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void createDB() throws SQLException {
		String dbStmt = "CREATE DATABASE IF NOT EXISTS " + SCHEMA;
		conn.prepareStatement(dbStmt).execute();
		conn.commit();
	}

	public static MySQLDB getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
