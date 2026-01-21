package controlador;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.dao.ProductoDAO;
import modelo.entidad.Producto;

@WebServlet("/ProductoController")
public class ProductoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.seleccionar(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.seleccionar(req, resp);
	}

	private void seleccionar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1. obtener parametros
		int id  = Integer.parseInt(req.getParameter("id"));
		// 2. hablar con el modelo
		Producto prod = ProductoDAO.obtenerDetalles(id);
		// 3. llamar a la vista
		req.setAttribute("producto", prod);
		req.getRequestDispatcher("jsp/detalleproducto.jsp").forward(req, resp);
	}

}
