package dao;

import entidades.Usuario;

public interface IUsuarioDao {
	public Usuario obtenerUsuario(String nombreUsuario, String pass);
}
