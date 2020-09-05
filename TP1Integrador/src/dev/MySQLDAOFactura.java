package dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MySQLDAOFactura implements DAOFactura{
	private Connection conn;
	private DAOCliente clienteDAO;
	
	public MySQLDAOFactura() {
		conn = MySQLDAOFactory.createConnection();
		try {
			builtTable();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		clienteDAO = new MySQLDAOCliente();
	}
	private void builtTable() throws SQLException {
		
		String tableStmt = "CREATE TABLE IF NOT EXISTS factura ("
				+ "idFactura INTEGER, idCliente INTEGER, "
				+ "PRIMARY KEY(idFactura), "
				+ "FOREIGN KEY (idCliente) REFERENCES cliente(idCliente))";
		conn.prepareStatement(tableStmt).execute();
		conn.commit();
		
		tableStmt = "CREATE TABLE IF NOT EXISTS factura_producto (" 
				+ "idFactura INTEGER, idProducto INTEGER, cantidad INTEGER, "
				+ "FOREIGN KEY (idFactura) REFERENCES factura(idFactura), "
				+ "FOREIGN KEY (idProducto) REFERENCES producto(idProducto))";
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
		Factura result = null;
		String select = "SELECT * FROM factura WHERE idFactura = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Cliente cliente = clienteDAO.findCliente(rs.getInt(2));
				result = new Factura(rs.getInt(1), cliente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean updateFactura(Factura p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Factura> selectFacturas() {
		Collection<Factura> result = new ArrayList<Factura>();
		String select = "SELECT * FROM factura";
		
		try {
			PreparedStatement ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Cliente cliente = clienteDAO.findCliente(rs.getInt(2));
				Factura f = new Factura(rs.getInt(1), cliente);
				result.add(f);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	@Override
	public void insertFacturaProducto(Factura f, Producto p, int cantidad) {
		try {
			String insertStmt = "INSERT INTO factura_producto(idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
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
