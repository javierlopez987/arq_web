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

	public static void main(String[] args) {
		DAOFactory mysqlFactory = DAOFactory.getDAOFactory();

		clienteDAO = mysqlFactory.getDAOCliente();
		productoDAO = mysqlFactory.getDAOProducto();
		facturaDAO = mysqlFactory.getDAOFactura();
		
		/**
		 * Se cargan los datos de clientes.csv 
		 * si la tabla en la BD está vacía
		 */
		Collection<Cliente> clientes = clienteDAO.selectClientes();
		if(clientes.isEmpty()) {
			cargarClientes();
		}
		
		/**
		 * Se cargan los datos de productos.csv 
		 * si la tabla en la BD está vacía
		 */
		Collection<Producto> productos = productoDAO.selectProducto();
		if(productos.isEmpty()) {
			cargarProductos();			
		}
		
		/**
		 * Se cargan los datos de factura.csv y factura-productos 
		 * si las tablas en la BD está vacía
		 */
		Collection<Factura> facturas = facturaDAO.selectFacturas();
		if(facturas.isEmpty()) {
			cargarFacturas();
			cargarFacturasProductos();			
		}
		
		/**
		 * Utiliza el servicio que retorna el producto que más recaudó
		 * y lo imprime por pantalla
		 */
		Producto mejorProducto = productoDAO.productoMayorRecaudacion();
		System.out.println(mejorProducto);
		
		/**
		 * Utiliza el servicio que lista clientes de mayor a menor facturación
		 * y los imprime por pantalla
		 */
		Collection<Cliente> mejoresClientes = clienteDAO.clientesMayorFacturacion();
		System.out.println(mejoresClientes);
		
		/**
		 * Cierra la conexion a la BD
		 */
		try {
			mysqlFactory.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Función que inserta línea por línea los datos de clientes.csv en la BD
	 */
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

	/**
	 * Función que inserta línea por línea los datos de productos.csv en la BD
	 */
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
	
	/**
	 * Función que inserta línea por línea 
	 * los datos de facturas.csv en la BD
	 * Se debe ejecutar despues de haber cargado los clientes
	 */
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
	
	/**
	 * Función que inserta línea por línea 
	 * los datos de factura-productos.csv en la BD
	 * Se debe ejecutar después de cargar los productos, los clientes y las facturas
	 */
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
