package dev;

import java.util.Collection;

public interface DAOCliente {
	
	int insertCliente(Cliente p);
	
	boolean deleteCliente(Cliente p);
	
	Cliente findCliente(int idCliente);
	
	boolean updateCliente(Cliente p);
	
	Collection<Cliente> selectClientes();
}
