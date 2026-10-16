package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidades.Usuario;

public class UsuarioDao implements IUsuarioDao{

	
	
	public Usuario buscarPorCredenciales(String usuario, String contrasena) {
		Usuario encontrado = null;
		Connection conexion = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT idUsuario, usuario, contrasena FROM usuarios WHERE usuario = ? AND contrasena = ?";

		try {
			conexion = Conexion.obtenerConexion();
			ps = conexion.prepareStatement(sql);
			ps.setString(1, usuario);
			ps.setString(2, contrasena);
			rs = ps.executeQuery();

			if (rs.next()) {
				encontrado = new Usuario();
				encontrado.setIdUsuario(rs.getInt("idUsuario"));
				encontrado.setUsuario(rs.getString("usuario"));
				encontrado.setContrasena(rs.getString("contrasena"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion(conexion, ps, rs);
		}

		return encontrado;
	}

	@Override
	public Usuario obtenerUsuario(String nombreUsuario, String pass) {
		Usuario user = null;
		String query = "select id_usuario, usuario, contrasena from usuarios where usuario = ? and contrasena = ?";
		
		Connection cn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			cn = Conexion.obtenerConexion();
			pst = cn.prepareStatement(query);
			
			pst.setString(1, nombreUsuario);
			pst.setString(2, pass);
			
			try {
				rs = pst.executeQuery();
				
				if (rs.next()) {
					user = new Usuario();
					user.setIdUsuario(rs.getInt("id_usuario"));
					user.setUsuario(rs.getString("usuario"));
					user.setContrasena(rs.getString("contrasena"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
