package main;

import dao.CategoriaDao;
import dao.ProductoDao;
import entidad.Categoria;
import entidad.Producto;
import java.util.List;

public class Principal {

	public static void main(String[] args) {
		CategoriaDao categoriaDao = new CategoriaDao();
		ProductoDao productoDao = new ProductoDao();

		// INSERT DE LA CATEGORIA
		// INSERT DE LA CATEGORIA
		
		/*
		 * Categoria cat1 = new Categoria("Electrónica");
		 * categoriaDao.agregarCategoria(cat1);
		 * 
		 * // INSERT DE UN PRODUCTO // INSERT DE UN PRODUCTO Producto producto1 = new
		 * Producto(); producto1.setCodigo("002");
		 * producto1.setNombre("Samsung Galaxy S24"); producto1.setPrecio(950.0);
		 * producto1.setStock(15); producto1.setIdCategoria(1);
		 * 
		 * int filas = productoDao.agregarProducto(producto1);
		 * 
		 * if (filas == 1) { System.out.println("Producto agregado con éxito."); } else
		 * { System.out.println("No se pudo agregar el producto."); }
		 */
		 

		// DELETE DE UNA CATEGORIA POR ID
		// DELETE DE UNA CATEGORIA POR ID
		/*
		 * System.out.println("CATEGORÍAS ANTES DE ELIMINAR:"); for (Categoria cat :
		 * categoriaDao.obtenerTodas()) { System.out.println(cat.getIdCategoria() +
		 * " - " + cat.getNombre()); }
		 * 
		 * Categoria cat2 = new Categoria("Vinícola");
		 * categoriaDao.agregarCategoria(cat2);
		 * 
		 * System.out.println("ID generado: " + cat2.getIdCategoria());
		 * 
		 * System.out.println("\nCategoría creada:");
		 * System.out.println(cat2.getIdCategoria() + " - " + cat2.getNombre());
		 * 
		 * boolean eliminado = categoriaDao.eliminarCategoria(cat2);
		 * 
		 * System.out.println("\n¿Se eliminó correctamente? " + eliminado);
		 * 
		 * System.out.println("\nCATEGORÍAS DESPUÉS DE ELIMINAR:"); for (Categoria cat :
		 * categoriaDao.obtenerTodas()) { System.out.println(cat.getIdCategoria() +
		 * " - " + cat.getNombre()); }
		 */

		// MODIFICACIÓN DE UNA CATEGORIA POR ID
		// MODIFICACIÓN DE UNA CATEGORIA POR ID
		/*
		 * Categoria cat3 = new Categoria("Bebidas");
		 * categoriaDao.agregarCategoria(cat3);
		 * 
		 * System.out.println("Categoría creada:");
		 * System.out.println(cat3.getIdCategoria() + " - " + cat3.getNombre());
		 * 
		 * cat3.setNombre("Bebidas Alcohólicas");
		 * 
		 * boolean modificado = categoriaDao.modificarCategoria(cat3);
		 * 
		 * System.out.println("\n¿Se modificó correctamente? " + modificado);
		 */

		// El punto D. El nombre lleva apostrofe a proposito, para ver que el SP lo
		// guarda
		// igual y no rompe la sentencia
		/*
		 * Producto producto2 = new Producto("003", "Notebook Lenovo 14' IdeaPad",
		 * 890.50, 8, 1);
		 * 
		 * if (productoDao.agregarProductoConSp(producto2)) {
		 * System.out.println("Producto agregado con el procedimiento almacenado."); }
		 * else { System.out.
		 * println("No se pudo agregar el producto con el procedimiento almacenado."); }
		 */

		// public class Principal {

		// public static void main(String[] args) {

		/*
		 * ProductoDao pDao = new ProductoDao(); List<Producto> lista =
		 * pDao.obtenerTodos();
		 * 
		 * System.out.println("=== LISTADO DE PRODUCTOS EN BASE DE DATOS ==="); if
		 * (lista.isEmpty()) { System.out.
		 * println("La lista está vacía o no se pudo conectar a la base de datos."); }
		 * else { for (Producto p : lista) { System.out.println("Código: " +
		 * p.getCodigo() + " | Nombre: " + p.getNombre() + " | Precio: $" +
		 * p.getPrecio() + " | Stock: " + p.getStock() + " | ID Cat: " +
		 * p.getIdCategoria()); } }
		 */
		
		// LISTADO ANTES
		System.out.println("PRODUCTOS ANTES DE MODIFICAR:");
		for (Producto p : productoDao.obtenerTodos()) {
		    System.out.println(p.getCodigo() + " - " + p.getNombre() + " - $" + p.getPrecio() + " - Stock: " + p.getStock());
		}

		// MODIFICACIÓN
		Producto productoAModificar = new Producto();
		productoAModificar.setCodigo("002"); // el cod de un producto que ya exista en la base
		productoAModificar.setNombre("Samsung Galaxy S24 Ultra");
		productoAModificar.setPrecio(1050.0);
		productoAModificar.setStock(12);
		productoAModificar.setIdCategoria(1);

		boolean modificado = productoDao.modificarProducto(productoAModificar);
		System.out.println("\n¿Se modificó correctamente? " + modificado);

		System.out.println("\nPRODUCTOS DESPUÉS DE MODIFICAR:");
		for (Producto p : productoDao.obtenerTodos()) {
		    System.out.println(p.getCodigo() + " - " + p.getNombre() + " - $" + p.getPrecio() + " - Stock: " + p.getStock());
		}

		// BAJA
		boolean eliminado = productoDao.eliminarProducto(productoAModificar);
		System.out.println("\n¿Se eliminó correctamente? " + eliminado);

		System.out.println("\nPRODUCTOS DESPUÉS DE ELIMINAR:");
		for (Producto p : productoDao.obtenerTodos()) {
		    System.out.println(p.getCodigo() + " - " + p.getNombre() + " - $" + p.getPrecio() + " - Stock: " + p.getStock());
		}
	}
}
