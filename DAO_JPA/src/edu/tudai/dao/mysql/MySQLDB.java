package edu.tudai.dao.mysql;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLDB {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URI = "jdbc:mysql://localhost:3306/db_arq_web_jpa";
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static MySQLDB getInstance() {
		return instance;
	}
	
	public Connection getConnection() {
		return conn;
	}
	
}
