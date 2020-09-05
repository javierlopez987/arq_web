package dev;

import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Programa {
	public static DAOCliente clienteDAO;
	public static DAOFactura facturaDAO;
	public static DAOProducto productoDAO;
	public static DAOManager managerDAO;

	public static void main(String[] args) {
		DAOFactory mysqlFactory = DAOFactory.getDAOFactory();

		clienteDAO = mysqlFactory.getDAOCliente();
		productoDAO = mysqlFactory.getDAOProducto();
		facturaDAO = mysqlFactory.getDAOFactura();
		managerDAO = mysqlFactory.getDAOManager();
		
		Collection<Cliente> clientes = clienteDAO.selectClientes();
		if(clientes.isEmpty()) {
			cargarClientes();
		}
		Collection<Producto> productos = productoDAO.selectProducto();
		if(productos.isEmpty()) {
			cargarProductos();			
		}
		
		Collection<Factura> facturas = facturaDAO.selectFacturas();
		if(facturas.isEmpty()) {
			cargarFacturas();
			cargarFacturasProductos();			
		}
		
		try {
			mysqlFactory.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public static void cargarClientes() {
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

	public static void cargarProductos() {
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
	
	public static void cargarFacturas() {
		String path = "csv/facturas.csv";
		
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));

			for (CSVRecord row : parser) {
				int idFactura = new Integer(row.get("idFactura"));
				int idCliente = new Integer(row.get("idCliente"));
				Cliente cliente = clienteDAO.findCliente(idCliente);
				Factura factura = new Factura(idFactura, cliente);
				facturaDAO.insertFactura(factura);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void cargarFacturasProductos() {
		String path = "csv/facturas-productos.csv";
		
		CSVParser parser;
		try {
			parser = CSVFormat.DEFAULT.withHeader().parse(new FileReader(path));

			for (CSVRecord row : parser) {
				int idFactura = new Integer(row.get("idFactura"));
				int idProducto = new Integer(row.get("idProducto"));
				int cant = new Integer(row.get("cantidad"));
				Producto producto = productoDAO.findProducto(idProducto);
				Factura factura = facturaDAO.findFactura(idFactura);
				facturaDAO.insertFacturaProducto(factura, producto, cant);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
