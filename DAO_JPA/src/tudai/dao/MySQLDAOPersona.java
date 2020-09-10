package tudai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import tudai.dao.model.Persona;

public class MySQLDAOPersona implements DAOPersona {
	private Connection conn;

	public MySQLDAOPersona() {
		conn = MySQLDAOFactory.createConnection();
	}

	@Override
	public int insertPersona(Persona p) {
		int result = -1;

		try {
			String insertStmt = "INSERT INTO persona (id, nombre, edad, dni) VALUES (?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(insertStmt);
			ps.setInt(1, p.getId());
			ps.setString(2, p.getNombre());
			ps.setInt(3, p.getEdad());
			ps.setInt(4, p.getDni());
			result = ps.executeUpdate();
			ps.close();
			conn.commit();
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
	public Persona findPersona(int id) {
		Persona result = null;
		String select = "SELECT * FROM persona WHERE id = ?";

		try {
			PreparedStatement ps = conn.prepareStatement(select);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				 result = new Persona(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4));
			}
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
	public Collection<Persona> selectPersonas() {
		Collection<Persona> result = new ArrayList<Persona>();
		String select = "SELECT * FROM persona";
		
		try {
			PreparedStatement ps = conn.prepareStatement(select);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Persona p = new Persona(rs.getInt("id"), rs.getString("nombre"), rs.getInt("edad"), rs.getInt("dni"));
				result.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
