package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class BaseDeDatos {

	public static void main(String[] args) {
		try {
			new Driver();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String uri = "jdbc:mysql://localhost:3306/db_casino";
		
		try {
			Connection conn = DriverManager.getConnection(uri);
			conn.setAutoCommit(false);
			createTables(conn);
			addPerson(conn, 1, "Inés", 20);
			addPerson(conn, 2, "Javier", 22);
			addPerson(conn, 3, "Ignacio", 15);
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private static void addPerson(Connection conn, int id, String name, int age) throws SQLException {
		String insert = "INSERT INTO persona (id, nombre, edad) VALUES (?, ?, ?)";
		PreparedStatement ps = conn.prepareStatement(insert);
		ps.setInt(1, id);
		ps.setString(2, name);
		ps.setInt(3, age);
		ps.executeUpdate();
		ps.close();
		conn.commit();
		
	}

	private static void createTables(Connection conn) throws SQLException {
		String table = "CREATE TABLE persona(" +
				"id INTEGER, nombre VARCHAR(60), edad INTEGER, PRIMARY KEY(id))";
		
		conn.prepareStatement(table).execute();
		conn.commit();
		
	}

}
