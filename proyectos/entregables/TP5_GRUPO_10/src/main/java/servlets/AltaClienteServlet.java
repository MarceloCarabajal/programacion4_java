package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import entidades.Cliente;
import negocio.ClienteNegocio;

@WebServlet("/AltaClienteServlet")
public class AltaClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AltaClienteServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dni = request.getParameter("txtDni");
        String cuil = request.getParameter("txtCuil");
        String nombre = request.getParameter("txtNombre");
        String apellido = request.getParameter("txtApellido");
        String sexo = request.getParameter("txtSexo");
        String fechaNacimiento = request.getParameter("txtFechaNacimiento");
        String direccion = request.getParameter("txtDireccion");
        String nacionalidad = request.getParameter("txtNacionalidad");
        String localidad = request.getParameter("txtLocalidad");
        String provincia = request.getParameter("txtProvincia");
        String correo = request.getParameter("txtCorreo");
        String telefono = request.getParameter("txtTelefono");

        Cliente cliente = new Cliente();
        cliente.setDni(dni);
        cliente.setCuil(cuil);
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setSexo(sexo);
        cliente.setFechaNacimiento(fechaNacimiento);
        cliente.setDireccion(direccion);
        cliente.setNacionalidad(nacionalidad);
        cliente.setLocalidad(localidad);
        cliente.setProvincia(provincia);
        cliente.setCorreoElectronico(correo);
        cliente.setTelefono(telefono);

        ClienteNegocio clienteNegocio = new ClienteNegocio();
        boolean insertado = clienteNegocio.insertar(cliente);

        if (insertado) {
            request.setAttribute("mensaje", "¡Cliente registrado con éxito!");
        } else {
            request.setAttribute("mensaje", "Error: No se pudo registrar el cliente.");
        }

        request.getRequestDispatcher("AltaCliente.jsp").forward(request, response);
    }
}
