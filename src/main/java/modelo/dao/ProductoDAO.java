package modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import modelo.entidad.Producto;

public class ProductoDAO {

	private static EntityManager em;

	/**
	 * Obtiene el EntityManager, creando la factoría si es necesario
	 */
	private static EntityManager getEntityManager() {
		if (em == null || !em.isOpen()) {
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
			em = emf.createEntityManager();
		}
		return em;
	}

	/**
	 * Obtiene los detalles de un producto por su ID
	 * 
	 * @param id ID del producto
	 * @return Producto encontrado o null
	 */
	public static Producto obtenerDetalles(int id) {
		try {
			EntityManager entityManager = getEntityManager();
			return entityManager.find(Producto.class, id);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Obtiene todos los productos de la base de datos
	 * 
	 * @return Lista de todos los productos
	 */
	public static List<Producto> obtenerProductos() {
		try {
			EntityManager entityManager = getEntityManager();
			Query query = entityManager.createQuery("SELECT p FROM Producto p", Producto.class);
			List<Producto> productos = query.getResultList();
			
			for (Producto p : productos) {
				System.out.println(p.toString());
			}
			return productos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Verifica si un producto está disponible
	 * 
	 * @param producto Producto a verificar
	 * @return true si está disponible, false en caso contrario
	 */
	public static boolean verificarDisponibilidad(Producto producto) {
		if (producto == null) {
			return false;
		}

		try {
			Producto p = obtenerDetalles(producto.getIdProducto());
			return p != null && p.isDisponibilidad();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Actualiza la disponibilidad de un producto (lo marca como no disponible)
	 * 
	 * @param producto Producto a actualizar
	 * @return true si se actualizó correctamente
	 */
	public static boolean actualizarInventario(Producto producto) {
		if (producto == null) {
			return false;
		}

		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			
			Producto p = entityManager.find(Producto.class, producto.getIdProducto());
			if (p != null) {
				p.setDisponibilidad(false);
				entityManager.merge(p);
				entityManager.getTransaction().commit();
				return true;
			}
			entityManager.getTransaction().rollback();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Busca un producto por nombre
	 * 
	 * @param nombre Nombre del producto a buscar
	 * @return Producto encontrado o null
	 */
	public static Producto buscar(String nombre) {
		if (nombre == null) {
			return null;
		}

		try {
			EntityManager entityManager = getEntityManager();
			Query query = entityManager.createQuery("SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(:nombre)", Producto.class);
			query.setParameter("nombre", "%" + nombre + "%");
			
			List<Producto> resultados = query.getResultList();
			return resultados.isEmpty() ? null : resultados.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Crea un nuevo producto en la base de datos
	 * 
	 * @param producto Producto a crear
	 * @return true si se creó correctamente
	 */
	public static boolean crearProducto(Producto producto) {
		if (producto == null) {
			return false;
		}

		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(producto);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Actualiza un producto existente
	 * 
	 * @param producto Producto a actualizar
	 * @return true si se actualizó correctamente
	 */
	public static boolean actualizarProducto(Producto producto) {
		if (producto == null) {
			return false;
		}

		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(producto);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Elimina un producto de la base de datos
	 * 
	 * @param id ID del producto a eliminar
	 * @return true si se eliminó correctamente
	 */
	public static boolean eliminarProducto(int id) {
		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			
			Producto p = entityManager.find(Producto.class, id);
			if (p != null) {
				entityManager.remove(p);
				entityManager.getTransaction().commit();
				return true;
			}
			entityManager.getTransaction().rollback();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
