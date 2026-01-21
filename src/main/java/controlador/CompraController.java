package controlador;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.dao.CompraDAO;
import modelo.dao.ProductoDAO;
import modelo.entidad.Producto;

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
		case "solicitarResumen":
			this.solicitarResumen(req, resp);
			break;
		case "agregarAlCarrito":
			this.agregarAlCarrito(req, resp);
			break;
		}
	}

	/**
	 * Agrega un producto al carrito de compras (sesión)
	 */
	private void agregarAlCarrito(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// 1. obtener parametros
			int idProducto = Integer.parseInt(req.getParameter("idProducto"));
			
			// 2. hablar con el modelo - obtener el producto
			Producto prod = ProductoDAO.obtenerDetalles(idProducto);
			
			if (prod == null) {
				resp.sendRedirect("jsp/mensajeerror.jsp?error=Producto no encontrado");
				return;
			}
			
			// 3. obtener sesión y agregar producto al carrito
			HttpSession sesion = req.getSession();
			List<Producto> carrito = (List<Producto>) sesion.getAttribute("carrito");
			
			if (carrito == null) {
				carrito = new ArrayList<>();
			}
			
			carrito.add(prod);
			sesion.setAttribute("carrito", carrito);
			sesion.setAttribute("totalProductos", carrito.size());
			
			// Redirigir al catálogo o al resumen
			String redirigir = req.getParameter("redirigir") == null ? "ExplorarCatalogoController" : req.getParameter("redirigir");
			resp.sendRedirect(redirigir);
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
			resp.sendRedirect("jsp/mensajeerror.jsp?error=ID de producto inválido");
		}
	}

	/**
	 * Confirma la compra: registra en BD y limpia el carrito
	 */
	private void confirmarCompra(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// 1. obtener sesión
			HttpSession sesion = req.getSession();
			List<Producto> carrito = (List<Producto>) sesion.getAttribute("carrito");
			
			// 2. validar que hay productos en el carrito
			if (carrito == null || carrito.isEmpty()) {
				req.setAttribute("error", "El carrito está vacío");
				req.getRequestDispatcher("jsp/mensajeerror.jsp").forward(req, resp);
				return;
			}
			
			// 3. hablar con el modelo - registrar la compra
			CompraDAO compraDAO = new CompraDAO();
			boolean registrado = compraDAO.registrarCompra(carrito);
			
			if (registrado) {
				// 4. limpiar el carrito de la sesión
				sesion.removeAttribute("carrito");
				sesion.removeAttribute("totalProductos");
				
				// 5. mostrar mensaje de éxito
				req.setAttribute("mensaje", "¡Compra realizada exitosamente!");
				req.getRequestDispatcher("jsp/resumencompra.jsp").forward(req, resp);
			} else {
				req.setAttribute("error", "Error al registrar la compra. Intente nuevamente.");
				req.getRequestDispatcher("jsp/mensajeerror.jsp").forward(req, resp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error al procesar la compra");
			try {
				req.getRequestDispatcher("jsp/mensajeerror.jsp").forward(req, resp);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * Muestra el resumen de la compra con los productos en el carrito
	 */
	private void solicitarResumen(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			// 1. obtener sesión
			HttpSession sesion = req.getSession();
			List<Producto> carrito = (List<Producto>) sesion.getAttribute("carrito");
			
			// 2. si no hay carrito, crear uno vacío
			if (carrito == null) {
				carrito = new ArrayList<>();
			}
			
			// 3. calcular el total
			float total = 0;
			for (Producto p : carrito) {
				total += p.getPrecio();
			}
			
			// 4. llamar a la vista
			req.setAttribute("carrito", carrito);
			req.setAttribute("total", total);
			req.setAttribute("cantidadProductos", carrito.size());
			req.getRequestDispatcher("jsp/resumencompra.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", "Error al obtener el resumen");
			try {
				req.getRequestDispatcher("jsp/mensajeerror.jsp").forward(req, resp);
			} catch (ServletException | IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
