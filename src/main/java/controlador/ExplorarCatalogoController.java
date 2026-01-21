package controlador;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.dao.ProductoDAO;
import modelo.entidad.Producto;

@WebServlet("/ExplorarCatalogoController")
public class ExplorarCatalogoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.ruteador(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		this.ruteador(req, resp);
	}

	private void ruteador(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String ruta = req.getParameter("ruta") == null ? "verListaProductos" : req.getParameter("ruta");

		switch (ruta) {
		case "buscarProducto":
			this.buscarProducto(req, resp);
			break;
		case "verListaProductos":
			this.verListaProductos(req, resp);
			break;
		}
	}

	private void verListaProductos(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ingreso a ver");

		// 1. obtener parametros
		// 2. hablar con el modelo
		List<Producto> listaProductos = ProductoDAO.obtenerProductos();
		// 3. llamar a la vista
		req.setAttribute("productos", listaProductos);
		req.getRequestDispatcher("jsp/pantallacatalogo.jsp").forward(req, resp);
	}

	private void buscarProducto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 1. obtener parametros
		String nombre = req.getParameter("productoBuscado");

		System.out.println(nombre);
		// 2. hablar con el modelo
		Producto productoBuscado = ProductoDAO.buscar(nombre);

		// 3. llamar a la vista
		if(productoBuscado == null) {
			resp.sendRedirect("jsp/mensajeerror.jsp");
		}
		else {
			int id = productoBuscado.getIdProducto();
			resp.sendRedirect("ProductoController?id="+id);
		}
	}

}
