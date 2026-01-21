package modelo.entidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idProducto;
	
	@Column (name = "nombre")
	private String nombre;
	
	@Column (name = "imagen")
	private String imagen;
	
	@Column (name = "descripcion")
	private String descripcion;
	
	@Column (name = "precio")
	private float precio;
	
	@Column (name = "condicion")
	private String condicion;
	
	@Column (name = "disponibilidad")
	private boolean disponibilidad;
	

	private static List<Producto> productos;

	public Producto() {
	}

	public Producto(String imagen, String descripcion, float precio, String condicion, boolean disponibilidad,
			int idProducto, String nombre) {
		super();
		this.imagen = imagen;
		this.descripcion = descripcion;
		this.precio = precio;
		this.condicion = condicion;
		this.disponibilidad = disponibilidad;
		this.idProducto = idProducto;
		this.nombre = nombre;
	}

	public String getImagen() {
		return imagen;
	}

	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public String getCondicion() {
		return condicion;
	}

	public void setCondicion(String condicion) {
		this.condicion = condicion;
	}

	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	public int getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(int idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombre=" + nombre + ", imagen=" + imagen + ", descripcion="
				+ descripcion + ", precio=" + precio + ", condicion=" + condicion + ", disponibilidad=" + disponibilidad
				+ "]";
	}
	
	
}
