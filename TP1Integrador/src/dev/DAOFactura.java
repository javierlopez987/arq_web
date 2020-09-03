package dev;

import java.util.Collection;

public interface DAOFactura {
	int insertFactura(Factura f);
	
	boolean deleteFactura(Factura f);
	
	Factura findFactura(int id);
	
	boolean updateFactura(Factura p);
	
	Collection<Factura> selectFacturas();
	
	void insertFacturaProducto(Factura f, Producto p, int cantidad);
}
