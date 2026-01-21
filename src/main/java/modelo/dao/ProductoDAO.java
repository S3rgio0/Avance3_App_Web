package modelo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.conexion.BddConnection;
import modelo.entidad.Producto;

public class ProductoDAO {
	
	private static List<Producto> productos;
	
	public static Producto obtenerDetalles(int id) {
		List<Producto> productos = obtenerProductos();

		for (Producto p : productos) {
			if (p.getIdProducto() == id) {
				return p;
			}
		}
		return null;
	}

	public static List<Producto> obtenerProductos() {
		
		String _SQL_GET_ALL_ = "SELECT * FROM PRODUCTO";
		try {
			PreparedStatement pstmt = BddConnection.getConexion().prepareStatement(_SQL_GET_ALL_);
			ResultSet rs = pstmt.executeQuery();
			
			List<Producto> productos = new ArrayList<>();
			
			while (rs.next()) {
			    productos.add(new Producto(
			        rs.getString("imagen"),                       // 1. imagen (varchar)
			        rs.getString("descripcion"),                  // 2. descripcion (varchar)
			        rs.getFloat("precio"),                        // 3. precio (float)
			        rs.getInt("condicion") == 1 ? "Nuevo" : "Usado", // 4. Convert tinyint back to String
			        rs.getBoolean("disponibilidad"),              // 5. disponibilidad (tinyint/boolean)
			        rs.getInt("idProducto"),                      // 6. idProducto (int)
			        rs.getString("nombre")                        // 7. nombre (varchar)
			    ));
			}
			BddConnection.cerrar(rs);
			BddConnection.cerrar(pstmt);
			BddConnection.cerrar();
			for(Producto p: productos) {
				System.out.println(p.toString());
			}
			return productos;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
		/*
		if (productos == null) {
			productos = new ArrayList<>();

			productos.add(new Producto("thriller.jpg", "Edición original de 1982, Epic Records", 45.00f, "Nuevo", true, 4, "Thriller"));

			productos.add(new Producto("discovery.jpg", "Doble LP, incluye artes originales", 55.25f, "Nuevo", true, 5, "Discovery"));

			productos.add(new Producto("cancion-animal.jpg", "Remasterizado en 180 gramos", 38.00f, "Nuevo", true, 6, "Canción Animal"));

			productos.add(new Producto("rumours.jpg", "Edición clásica, excelente estado", 29.99f, "Usado", true, 7, "Rumours"));

			productos.add(new Producto("tpab.jpg", "Gatefold edition, incluye libreto", 48.50f, "Nuevo", true, 8, "To Pimp a Butterfly"));

			productos.add(new Producto("back-to-black.jpg", "Edición estándar de alta fidelidad", 32.00f, "Nuevo", true, 9, "Back to Black"));

			productos.add(new Producto("nevermind.jpg", "30th Anniversary Edition con 7 pulgadas extra", 60.00f, "Nuevo", true, 10, "Nevermind"));

			productos.add(new Producto("bocanada.jpg", "Prensaje argentino, edición de lujo", 42.00f, "Usado", false, 11, "Bocanada"));

			productos.add(new Producto("midnights.jpg", "Moonstone Blue Edition, vinilo de color", 35.90f, "Nuevo", true, 12, "Midnights"));

			productos.add(new Producto("the-wall.jpg", "Prensaje original de 1979, primera edición", 150.00f, "Usado", true, 13, "The Wall"));
		}
		return productos;
		*/
	}

	public static boolean verificarDisponibilidad(Producto producto) {

		List<Producto> productos = obtenerProductos();

		if (producto == null) {
			return false;
		}

		for (Producto p : productos) {
			if (p.getIdProducto() == producto.getIdProducto()) {
				return p.isDisponibilidad();
			}
		}

		return false; // not found
	}

	public static boolean actualizarInventario(Producto producto) {
		List<Producto> productos = obtenerProductos();

		if (producto == null) {
			return false;
		}

		for (Producto p : productos) {
			if (p.getIdProducto() == producto.getIdProducto()) {
				p.setDisponibilidad(false);
				return true;
			}
		}

		return false; // not found
	}

	public static Producto buscar(String nombre) {
		List<Producto> productos = obtenerProductos();

		if (nombre == null) {
			return null;
		}

		for (Producto p : productos) {
			if (p.getNombre().equalsIgnoreCase(nombre)) {
				return p;
			}
		}

		return null; // not found
	}
}
