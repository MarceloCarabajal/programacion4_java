package negocio;
import dao.IUsuarioDao;
import dao.UsuarioDao;
import entidades.Usuario;

public class UsuarioNegocio {
	private IUsuarioDao userDao = new UsuarioDao();
	
	public Usuario validarUsuario(String nombreUsuario, String pass) {
		return userDao.obtenerUsuario(nombreUsuario, pass);
	}
}
