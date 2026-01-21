package controlador;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/CompraController")
public class CompraController extends HttpServlet {

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
		String ruta = req.getParameter("ruta") == null ? "solicitarResumen" : req.getParameter("ruta");
		
		switch(ruta) {
		case "confirmarCompra":
			this.confirmarCompra(req, resp);
			break;
		case "solicitarResuemn":
			this.solicitarResumen(req, resp);
			break;
		}
	}

	private void confirmarCompra(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 1. obtener parametros
		// 2. hablar con el modelo
		// 3. llamar a la vista
	}

	private void solicitarResumen(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// 1. obtener parametros
		// 2. hablar con el modelo
		// 3. llamar a la vista
	}

}
