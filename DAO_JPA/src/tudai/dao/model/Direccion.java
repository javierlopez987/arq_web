package tudai.dao.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Direccion {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column
	private String calle;
	@Column
	private String ciudad;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "domicilio")
	private List<Persona> convivientes;
	
	public Direccion() {
		super();
		this.convivientes = new ArrayList<Persona>();
	}

	public Direccion(String calle, String ciudad) {
		super();
		this.calle = calle;
		this.ciudad = ciudad;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Iterator<Persona> getConvivientes() {
		return convivientes.iterator();
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Direccion [id=" + id + ", calle=" + calle + ", ciudad=" + ciudad + "]";
	}
	
	
}
