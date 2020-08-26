package test;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {

	public static void main(String[] args) {
		String driver = "com.mysql.cj.jdbc.Driver";
		
		try {
			Class.forName(driver).getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		
		String uri = "jdbc:mysql://localhost:3306/db_arq_web";
		
		try {
			Connection conn = DriverManager.getConnection(uri, "root", "");
			conn.setAutoCommit(false);
			String select = "SELECT * FROM persona";
			PreparedStatement ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.print(rs.getInt(1));
				System.out.print(", ");
				System.out.print(rs.getString(2));
				System.out.print(", ");
				System.out.print(rs.getInt(3));
				System.out.println();
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();	
		}

	}

}
