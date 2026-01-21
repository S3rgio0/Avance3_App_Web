package modelo.entidad;

import java.io.Serializable;
import java.util.List;

public class Compra implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int idCompra;
	public static List<Producto> productos;
	
	public Compra () {}

	public Compra(int idCompra, List<Producto> productos) {
		super();
		this.idCompra = idCompra;
		this.productos = productos;
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	

}
