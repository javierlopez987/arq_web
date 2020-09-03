package dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MySQLDAOProducto implements DAOProducto{
	private Connection conn;
	
	public MySQLDAOProducto() {
		try {
			conn = MySQLDAOFactory.createConnection();
			builtTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void builtTable() throws SQLException {
		String tableStmt = "CREATE TABLE IF NOT EXISTS producto (" + 
				"idProducto INTEGER, nombre VARCHAR(100), valor DOUBLE, " + 
				"PRIMARY KEY(idProducto))";
		conn.prepareStatement(tableStmt).execute();
		conn.commit();
	}

	@Override
	public int insertProducto(Producto p) {
		int result = -1;

		try {
			String insertStmt = "INSERT INTO producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(insertStmt);
			ps.setInt(1, p.getIdProducto());
			ps.setString(2, p.getNombre());
			ps.setFloat(3, p.getValor());
			result = ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean deleteProducto(Producto p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Producto findProducto(int id) {
		Producto result = null;
		String select = "SELECT * FROM producto WHERE id = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				 result = new Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean updateProducto(Producto p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Producto> selectProducto() {
		Collection<Producto> result = new ArrayList<Producto>();
		String select = "SELECT * FROM producto";
		
		try {
			PreparedStatement ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Producto p = new Producto(rs.getInt(1), rs.getString(2), rs.getFloat(3));
				result.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
