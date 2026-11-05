package negocio;

import java.util.List;
import dao.ClienteDao;
import entidades.Cliente;

public class ClienteNegocio {
	private ClienteDao clienteDao = new ClienteDao();

    public boolean insertar(Cliente cliente) {
        return clienteDao.agregarCliente (cliente);
    }

    public List<Cliente> obtenerTodos() {
        return clienteDao.obtenerTodos();
    }

    public boolean existeDni(String dni) {
        return clienteDao.existeDni(dni);
    }

    public boolean existeCuil(String cuil) {
        return clienteDao.existeCuil(cuil);
    }
}