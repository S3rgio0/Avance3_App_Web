package modelo.entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "compra")
public class Compra implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idCompra;

	@Column(name = "fecha_compra")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCompra;

	@Column(name = "total")
	private float total;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "compra_producto", joinColumns = @JoinColumn(name = "idCompra"), inverseJoinColumns = @JoinColumn(name = "idProducto"))
	private List<Producto> productos = new ArrayList<>();

	public Compra() {
		this.fechaCompra = new Date();
	}

	public Compra(List<Producto> productos) {
		super();
		this.productos = productos;
		this.fechaCompra = new Date();
	}

	public int getIdCompra() {
		return idCompra;
	}

	public void setIdCompra(int idCompra) {
		this.idCompra = idCompra;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public String toString() {
		return "Compra [idCompra=" + idCompra + ", fechaCompra=" + fechaCompra + ", total=" + total + ", productos="
				+ productos.size() + "]";
	}
}
