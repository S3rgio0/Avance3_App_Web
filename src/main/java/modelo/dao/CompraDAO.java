package modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import modelo.entidad.Compra;
import modelo.entidad.Producto;

public class CompraDAO {

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
	 * Registra una nueva compra en la base de datos
	 * 
	 * @param productosComprados Lista de productos comprados
	 * @return true si se registró correctamente
	 */
	public boolean registrarCompra(List<Producto> productosComprados) {
		if (productosComprados == null || productosComprados.isEmpty()) {
			return false;
		}

		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();

			Compra compra = new Compra(productosComprados);
			
			// Calcular el total de la compra
			float total = 0;
			for (Producto p : productosComprados) {
				total += p.getPrecio();
			}
			compra.setTotal(total);

			entityManager.persist(compra);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Obtiene los datos de una compra por su ID
	 * 
	 * @param idCompra ID de la compra
	 * @return Compra encontrada o null
	 */
	public Compra obtenerDatos(int idCompra) {
		try {
			EntityManager entityManager = getEntityManager();
			return entityManager.find(Compra.class, idCompra);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Obtiene todas las compras de la base de datos
	 * 
	 * @return Lista de todas las compras
	 */
	public static List<Compra> obtenerTodasLasCompras() {
		try {
			EntityManager entityManager = getEntityManager();
			Query query = entityManager.createQuery("SELECT c FROM Compra c", Compra.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Actualiza una compra existente
	 * 
	 * @param compra Compra a actualizar
	 * @return true si se actualizó correctamente
	 */
	public static boolean actualizarCompra(Compra compra) {
		if (compra == null) {
			return false;
		}

		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(compra);
			entityManager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Elimina una compra de la base de datos
	 * 
	 * @param idCompra ID de la compra a eliminar
	 * @return true si se eliminó correctamente
	 */
	public static boolean eliminarCompra(int idCompra) {
		try {
			EntityManager entityManager = getEntityManager();
			entityManager.getTransaction().begin();

			Compra c = entityManager.find(Compra.class, idCompra);
			if (c != null) {
				entityManager.remove(c);
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
