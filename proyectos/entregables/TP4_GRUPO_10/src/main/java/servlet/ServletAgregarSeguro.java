package servlet;

import java.io.IOException;

import dao.SeguroDAO;
import dao.TipoSeguroDAO;
import entidad.Seguro;
import entidad.TipoSeguro;
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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("proximoId", seguroDAO.obtenerProximoId());
		request.setAttribute("listaTipos", tipoSeguroDAO.obtenerTiposSeguro());
		request.getRequestDispatcher("AgregarSeguro.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Sin esto, se rompen las ´palabras que tienen tilde.
		// Se coloca antes de leer los parametros
		request.setCharacterEncoding("UTF-8");

		String descripcion = request.getParameter("txtDescripcion");
		String mensaje;

		try {
			int idTipo = Integer.parseInt(request.getParameter("ddlTipoSeguro"));
			double costoContratacion = Double.parseDouble(request.getParameter("txtCostoContratacion").replace(',', '.'));
			double costoAsegurado = Double.parseDouble(request.getParameter("txtCostoAsegurado").replace(',', '.'));

			if (descripcion == null || descripcion.isBlank()) {
				mensaje = "La descripción no puede estar vacía.";
			} else if (costoContratacion < 0 || costoAsegurado < 0) {
				mensaje = "Los costos no pueden ser negativos.";
			} else {
				TipoSeguro tipo = new TipoSeguro();
				tipo.setIdTipo(idTipo);

				Seguro seguro = new Seguro();
				seguro.setDescripcion(descripcion);
				seguro.setTipoSeguro(tipo);
				seguro.setCostoContratacion(costoContratacion);
				seguro.setCostoAsegurado(costoAsegurado);

				if (seguroDAO.insertar(seguro)) {
					mensaje = "Seguro agregado correctamente.";
				} else {
					mensaje = "No se pudo agregar el seguro.";
				}
			}
		} catch (NumberFormatException e) {
			mensaje = "Los costos deben ser números válidos.";
		}

		request.setAttribute("mensaje", mensaje);
		doGet(request, response);
	}
}