package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import entidades.Usuario;

public class UsuarioDao {

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
}
