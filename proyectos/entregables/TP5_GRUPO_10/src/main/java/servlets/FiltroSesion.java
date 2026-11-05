package servlets;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


 /* Filtro que solo deja pasar a las pantallas protegidas 
  * si hay un usuario en sesión.
  * si no hay usuario con sesión, redirige al Login.
 */
@WebFilter(urlPatterns = {
		"/AltaCliente.jsp",
		"/ListarClientes.jsp",
		"/AltaClienteServlet",
		"/ListarClientesServlet"
})
public class FiltroSesion implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		HttpSession session = req.getSession(false);
		boolean logueado = session != null && session.getAttribute("usuarioLogueado") != null;

		if (logueado) {
			chain.doFilter(request, response);
		} else {
			res.sendRedirect(req.getContextPath() + "/Login.jsp");
		}
	}
}