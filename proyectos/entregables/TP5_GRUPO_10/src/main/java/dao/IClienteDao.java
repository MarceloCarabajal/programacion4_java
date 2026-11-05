package dao;

import java.util.List;

import entidades.Cliente;

public interface IClienteDao {
	public boolean agregarCliente(Cliente cliente);
	public List<Cliente> obtenerTodos();
	public boolean existeDni(String dni);
	public boolean existeCuil(String cuil);
}