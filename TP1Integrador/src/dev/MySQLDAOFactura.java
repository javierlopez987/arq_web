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
				+ "PRIMARY KEY(idFactura), "
				+ "FOREIGN KEY(idCliente) REFERENCES cliente(idCliente))";
		conn.prepareStatement(tableStmt).execute();
		
		tableStmt = "CREATE TABLE IF NOT EXISTS factura_producto (" 
				+ "idFactura INTEGER, idProducto INTEGER, cantidad INTEGER, " 
				+ "FOREIGN KEY(idFactura) REFERENCES factura(idFactura), "
				+ "FOREIGN KEY(idProducto) REFERENCES factura(idProducto))";
		conn.prepareStatement(tableStmt).execute();
		conn.commit();
	}
	@Override
	public int insertFactura(Factura f) {
		int result = -1;

		try {
			String insertStmt = "INSERT INTO factura (idFactura, idCliente) VALUES (?, ?)";
			PreparedStatement ps = conn.prepareStatement(insertStmt);
			ps.setInt(1, f.getIdFactura()());
			ps.setString(2, f.getCliente());
			ps.setString(3, f.getEmail());
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

}
