package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class Select {

	public static void main(String[] args) {
		try {
			new Driver();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String uri = "jdbc:mysql://localhost:3306/db_casino";
		
		try {
			Connection conn = DriverManager.getConnection(uri, "root", "");
			conn.setAutoCommit(false);
			String select = "SELECT * FROM juego";
			PreparedStatement ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				System.out.print(rs.getInt(1));
				System.out.print(" ");
				System.out.println(rs.getString(2));
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();	
		}

	}

}
