package dev;

import java.util.Collection;

public interface DAOProducto {
	
	int insertProducto(Producto p);
	
	boolean deleteProducto(Producto p);
	
	Producto findProducto(int id);
	
	boolean updateProducto(Producto p);
	
	Collection<Producto> selectProducto();
	
	Producto productoMayorRecaudacion();
}