package servlet;

import java.io.IOException;

import dao.SeguroDAO;
import dao.TipoSeguroDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/servletAgregarSeguro")
public class ServletAgregarSeguro extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SeguroDAO seguroDAO = new SeguroDAO();
	private TipoSeguroDAO tipoSeguroDAO = new TipoSeguroDAO();

	// Carga los datos de la pantalla de alta.
	// Falta en AgregarSeguro.jsp: mostrar proximoId y listaTipos, y el formulario
	// (descripcion, costos y boton). El enlace Agregar Seguros tiene que apuntar a este servlet.
	// Falta doPost: leer los datos del formulario y llamar a SeguroDAO.insertar.
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("proximoId", seguroDAO.obtenerProximoId());
		request.setAttribute("listaTipos", tipoSeguroDAO.obtenerTiposSeguro());
		request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
	}
}
