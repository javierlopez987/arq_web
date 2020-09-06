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

	/**
	 * Función que crea la tabla cliente, si no existe, en la BD.
	 */
	private void builtTable() throws SQLException {
		String tableStmt = "CREATE TABLE IF NOT EXISTS cliente (" + 
				"idCliente INTEGER, nombre VARCHAR(100), email VARCHAR(100), " + 
				"PRIMARY KEY(idCliente))";
		conn.prepareStatement(tableStmt).execute();
		conn.commit();
	}

	/**
	 * Función que, dado un objeto Cliente,
	 * persiste sus datos en la BD
	 */
	@Override
	public int insertCliente(Cliente p) {
		int result = -1;

		try {
			String insertStmt = "INSERT INTO cliente (idCliente, nombre, email) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(insertStmt);
			ps.setInt(1, p.getIdCliente());
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

	/**
	 * Función que, dado un idCliente por parámetro, 
	 * retorna una objeto Cliente si existe, o null si no existe,
	 * con los correspondientes datos contenidos en la BD
	 */
	@Override
	public Cliente findCliente(int idCliente) {
		Cliente result = null;
		String select = "SELECT * FROM cliente WHERE idCliente = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, idCliente);
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

	/**
	 * Retorna una colección de todos los clientes contenidos en la BD.
	 */
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

	/**
	 * Función que retorna una lista de clientes de mayor a menor facturación
	 * Implementación: crea las vistas correspondientes si no existen,
	 * luego realiza la consulta correspondiente.
	 */
	@Override
	public Collection<Cliente> clientesMayorFacturacion() {
		Collection<Cliente> result = new ArrayList<Cliente>();
		String createView = "CREATE VIEW IF NOT EXISTS vw_factura_importe_total AS "
				+ "SELECT f.*, SUM(cantidad * valor) AS total_factura "
				+ "FROM factura f "
				+ "INNER JOIN factura_producto fp ON f.idFactura = fp.idFactura "
				+ "INNER JOIN producto p ON fp.idProducto = p.idProducto "
				+ "GROUP BY f.idFactura "
				+ "HAVING SUM(cantidad * valor) "
				+ "ORDER BY total_factura DESC; ";
		
		String createView2 = "CREATE VIEW IF NOT EXISTS vw_clientes_mayor_facturacion AS "
				+ "SELECT c.*, SUM(total_factura) AS total_facturado "
				+ "FROM cliente c "
				+ "INNER JOIN vw_factura_importe_total f ON c.idCliente = f.idCliente "
				+ "GROUP BY c.idCliente "
				+ "HAVING SUM(total_factura) "
				+ "ORDER BY total_facturado DESC; ";
		
		String select = "SELECT * "
				+ "FROM vw_clientes_mayor_facturacion; ";
		
		try {
			PreparedStatement ps = conn.prepareStatement(createView);
			ps.execute();
			ps.close();
			conn.commit();
			
			ps = conn.prepareStatement(createView2);
			ps.execute();
			ps.close();
			conn.commit();
			
			ps = conn.prepareStatement(select);
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
