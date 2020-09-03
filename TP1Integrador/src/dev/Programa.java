package dev;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Programa {

	public static void main(String[] args) {
		DAOFactory mysqlFactory = DAOFactory.getDAOFactory();

		DAOCliente clienteDAO = mysqlFactory.getDAOCliente();
		
		cargarClientes(clienteDAO);

		Collection<Cliente> clientes = clienteDAO.selectClientes();

		for (Cliente p : clientes) {
			System.out.println(p);
		}
		
		DAOProducto productoDAO = mysqlFactory.getDAOProducto();
		
		cargarProductos(productoDAO);
		
		Collection<Producto> producto = productoDAO.selectProducto();

		for (Producto p : producto) {
			System.out.println(p);
		}
		
	}
	
	

	public static void cargarClientes(DAOCliente clienteDAO) {
		String path = "csv/clientes.csv";
		
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));

			for (CSVRecord row : parser) {
				int id = new Integer(row.get("idCliente"));
				String nombre = row.get("nombre");
				String email = row.get("email");
				Cliente cliente = new Cliente(id, nombre, email);
				clienteDAO.insertCliente(cliente);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void cargarProductos(DAOProducto productoDAO) {
		String path = "csv/productos.csv";
		
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));

			for (CSVRecord row : parser) {
				int idProducto = new Integer(row.get("idProducto"));
				String nombre = row.get("nombre");
				float valor = new Float(row.get("valor"));
				Producto producto = new Producto(idProducto, nombre, valor);
				productoDAO.insertProducto(producto);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public static void leerCSV(String path) {
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));

			for (CSVRecord row : parser) {
				for (String e : row) {
					System.out.print(e);
				}
				System.out.println();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
