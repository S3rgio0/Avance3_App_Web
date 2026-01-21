package modelo.dao;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import modelo.entidad.Producto;

public class GestorProductoDAO {
	
	private static GestorProductoDAO INSTANCE;
	
	EntityManager em;
	
	
	private GestorProductoDAO() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistencia");
		this.em = emf.createEntityManager();
	}
	
	public static GestorProductoDAO getGestorProducto() {
		if(INSTANCE == null)
			INSTANCE = new GestorProductoDAO();
		return INSTANCE;
	}
	
	public boolean createProduct(Producto p) {
		this.em.getTransaction().begin();
		try {
			this.em.persist(p);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if(this.em.getTransaction().isActive())
				this.em.getTransaction().rollback();
			return false;
		}
	}
	
	public Producto reedProduct(int id) {
		return this.em.find(Producto.class, id);
	}
	
	public List<Producto> reedProducts() {
		return null;
	}
	
	public boolean updateProduct(Producto p) {
		this.em.getTransaction().begin();
		try {
			this.em.merge(p);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			return false;
		}
	}
	
	public boolean deleteProduct(int id) {
		Producto p = this.reedProduct(id);
		this.em.getTransaction().begin();
		try {
			this.em.remove(p);
			this.em.getTransaction().commit();
			return true;
		} catch (Exception e) {
			if(em.getTransaction().isActive())
				em.getTransaction().rollback();
			return false;
		}
	}

}