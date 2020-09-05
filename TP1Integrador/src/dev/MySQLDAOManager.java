package dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import com.mysql.cj.xdevapi.Statement;

public class MySQLDAOManager implements DAOManager {
	private Connection conn;
	
	public MySQLDAOManager(){
		//conn = MySQLDAOFactory.createConnection();
		try {
			conn = MySQLDAOFactory.createConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Producto recaudacionMayor() throws SQLException {
		conn.setAutoCommit(false);
		String query = "SELECT idProducto "
						+ "FROM producto p"
						+ "WHERE NOT EXIST("
						+ "SELECT max(count(p.idProducto) * p.valor)"
						+ "FROM factura"
						+ "GROUP BY idProducto)";	// me devuelve el ID del producto que mas facturo
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs= ps.executeQuery();
		Producto recMayor = new Producto(rs.getInt(1),rs.getString(2),rs.getFloat(3));
		conn.close();
		return recMayor;
	}
	
	public Collection<Cliente> clientesXFacturacion() throws SQLException{
		conn.setAutoCommit(false);
		String query = "SELECT *)"
				+ "FROM cliente c"//Falta terminar consulta... hay q unir las 4 tablas...
				+ "ORDER BY (SELECT)";
		
		PreparedStatement ps = conn.prepareStatement(query);
		ResultSet rs= ps.executeQuery();
		Cliente cl= null;
		Collection <Cliente> clMayor= null;
		while(rs.next()) {
			cl = new Cliente(rs.getInt(1),rs.getString(2),rs.getString(3));
			clMayor.add(cl);//VER agregacion en collection
		}
		return clMayor;
	}

}
