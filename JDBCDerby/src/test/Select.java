package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.derby.jdbc.EmbeddedDriver;

public class Select {

	public static void main(String[] args) {
		new EmbeddedDriver();
		
		String uri = "jdbc:derby:MyDerbyDB;create=true";
		
		try {
			Connection conn = DriverManager.getConnection(uri);
			String select = "SELECT * FROM persona";
			PreparedStatement ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1) + ", " + rs.getString(2) + ", " + rs.getInt(3));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();	
		}

	}

}
