package dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MySQLDAOCliente implements DAOCliente{
	private Connection conn;
	
	public MySQLDAOCliente() {
		try {
			conn = MySQLDAOFactory.createConnection();
			builtTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void builtTable() throws SQLException {
		String tableStmt = "CREATE TABLE IF NOT EXISTS cliente (" + 
				"id INTEGER, nombre VARCHAR(100), email VARCHAR(100), " + 
				"PRIMARY KEY(id))";
		conn.prepareStatement(tableStmt).execute();
		conn.commit();
	}

	@Override
	public int insertCliente(Cliente p) {
		int result = -1;

		try {
			String insertStmt = "INSERT INTO cliente (id, nombre, email) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(insertStmt);
			ps.setInt(1, p.getId());
			ps.setString(2, p.getNombre());
			ps.setString(3, p.getEmail());
			result = ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean deleteCliente(Cliente p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Cliente findCliente(int id) {
		Cliente result = null;
		String select = "SELECT * FROM cliente WHERE id = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				 result = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean updateCliente(Cliente p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Cliente> selectClientes() {
		Collection<Cliente> result = new ArrayList<Cliente>();
		String select = "SELECT * FROM cliente";
		
		try {
			PreparedStatement ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Cliente p = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3));
				result.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
