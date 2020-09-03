package dev;

import java.util.HashMap;
import java.util.Map;

public class Factura {
	private int idFactura;
	private Cliente cliente;
	private Map<Producto, Integer> productos;
	
	public Factura(int idFactura, Cliente cliente) {
		this.idFactura = idFactura;
		this.cliente = cliente;
		this.productos = new HashMap<Producto, Integer>();
	}
	
	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getCliente() {
		return cliente.getIdCliente();
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public int addProducto(Producto p) {
		int cant = 1;
		
		if(productos.containsKey(p)) {
			cant = cant + productos.get(p);
			productos.put(p, cant);
		} else {
			productos.put(p, cant);
		}
		
		return cant;
	}
	
	public void removeProducto(Producto p) {
		int cant = 0;
		
		if(productos.containsKey(p)) {
			cant = productos.get(p);
			if (cant > 0) {
				cant--;
				productos.put(p, cant);
			}
		}
	}
	
	public int getCantidad(Producto p) {
		int result = 0;
		
		if(productos.containsKey(p)) {
			result = productos.get(p);
		}
		
		return result;
	}
}
