package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import dao.SeguroDAO;
import dao.TipoSeguroDAO;
import entidad.Seguro;

/**
 * Servlet implementation class ServletListarSeguros
 */
@WebServlet("/ServletListarSeguros")
public class ServletListarSeguros extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SeguroDAO seguroDAO = new SeguroDAO();
	private TipoSeguroDAO tipoSeguroDAO = new TipoSeguroDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	/*
    public ServletListarSeguros() {
        super();
        // TODO Auto-generated constructor stub
    }
    */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 0 significa "sin filtro" (todos los tipos)
		int idTipo = 0;
		String parametro = request.getParameter("ddlTipoSeguro");
		if (parametro != null && !parametro.isEmpty()) {
			try {
				idTipo = Integer.parseInt(parametro);
			} catch (NumberFormatException e) {
				idTipo = 0;
			}
		}

		List<Seguro> listaSeguros;
		if (idTipo > 0) {
			listaSeguros = seguroDAO.listarPorTipo(idTipo);
		} else {
			listaSeguros = seguroDAO.listarTodos();
		}

		request.setAttribute("listaSeguros", listaSeguros);
		request.setAttribute("listaTipos", tipoSeguroDAO.obtenerTiposSeguro());
		request.setAttribute("tipoSeleccionado", idTipo);
		request.getRequestDispatcher("ListarSeguros.jsp").forward(request, response);
	}
	
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	/*
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	*/

}
