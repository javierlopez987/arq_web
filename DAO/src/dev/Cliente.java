package dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cliente {

	public static void main(String[] args) {
		
		try {
			Connection conn = MySQLDB.getInstance().getConnection();
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
