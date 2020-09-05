package dev;

import java.util.Collection;

public interface  DAOManager {
	
	Producto recaudacionMayor();
	
	Collection<Cliente> clientesXFacturacion();

}
