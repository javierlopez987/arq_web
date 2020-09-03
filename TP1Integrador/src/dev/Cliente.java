package dev;

public class Cliente {
	private int idCliente;
	private String nombre;
	private String email;
	
	public Cliente(int idCliente, String nombre, String email) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.email = email;
	}

	public int getId() {
		return idCliente;
	}

	public void setId(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String toString() {
		return " idCliente: " + this.idCliente + " Nombre: " + this.nombre + " Email: " + this.email;
	}
}
