package dev;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MySQLDAOPersona implements DAOPersona {
	private Connection conn;

	public MySQLDAOPersona() {
		conn = MySQLDAOFactory.createConnection();
	}

	@Override
	public int insertPersona(Persona p) {
		int result = -1;

		try {
			String insertStmt = "INSERT INTO persona (nombre, edad, dni) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(insertStmt);
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setInt(3, p.getDni());
			ps.executeUpdate();
			ps.close();
			conn.commit();
			result = findPersona(p).getId();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean deletePersona(Persona p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Persona findPersona(Persona p) {
		Persona result = null;
		String select = "SELECT * FROM persona WHERE dni = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, p.getDni());
			ResultSet rs = ps.executeQuery();
			int aux = 0;
			while(rs.next()) {
				 aux = rs.getInt(1);
			}
			p.setId(aux);
			result = p;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	@Override
	public boolean updatePersona(Persona p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Collection<Persona> selectPersonas(int edad) {
		Collection<Persona> result = new ArrayList<Persona>();
		String select = "SELECT * FROM persona WHERE edad = ?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, edad);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Persona p = new Persona(rs.getString("nombre"), rs.getInt("edad"), rs.getInt("dni"));
				result.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
