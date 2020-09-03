package dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

public class MySQLDAOFactura implements DAOFactura{
	private Connection conn;
	
	public MySQLDAOFactura() {
		conn = MySQLDAOFactory.createConnection();
		try {
			builtTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void builtTable() throws SQLException {
		String tableStmt = "CREATE TABLE IF NOT EXISTS factura ("
				+ "idFactura INTEGER, idCliente INTEGER, "
				+ "PRIMARY KEY(idFactura))";
		conn.prepareStatement(tableStmt).execute();
		conn.commit();
		
		tableStmt = "CREATE TABLE IF NOT EXISTS factura_producto (" 
				+ "idFactura INTEGER, idProducto INTEGER, cantidad INTEGER)";
		conn.prepareStatement(tableStmt).execute();
		conn.commit();
	}
	@Override
	public int insertFactura(Factura f) {
		int result = -1;

		try {
			String insertStmt = "INSERT INTO factura (idFactura, idCliente) VALUES (?, ?)";
			PreparedStatement ps = conn.prepareStatement(insertStmt);
			ps.setInt(1, f.getIdFactura());
			ps.setInt(2, f.getCliente());
			result = ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean deleteFactura(Factura f) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Factura findFactura(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateFactura(Factura p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Factura> selectFacturas() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void insertFacturaProducto(Factura f, Producto p, int cantidad) {
		try {
			String insertStmt = "INSERT INTO factura-producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(insertStmt);
			ps.setInt(1, f.getIdFactura());
			ps.setInt(2, p.getIdProducto());
			ps.setInt(3, cantidad);
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
