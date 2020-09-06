package dev;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDB {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URI = "jdbc:mysql://localhost:3306/";
	public static final String SCHEMA = "db_arq_web";
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
			conn = DriverManager.getConnection(URI, "root", "password");
			
			conn.setAutoCommit(false);
			
			createDB();
			
			conn.setCatalog(SCHEMA);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Crea la base de datos, si no existe.
	 */
	private void createDB() throws SQLException {
		String dbStmt = "CREATE DATABASE IF NOT EXISTS " + SCHEMA;
		conn.prepareStatement(dbStmt).execute();
		conn.commit();
	}

	/**
	 * Retorna la instancia de este singleton
	 */
	public static MySQLDB getInstance() {
		return instance;
	}
	
	/**
	 * Retorna la conexion a la DB
	 */
	public Connection getConnection() {
		return conn;
	}
	
}
