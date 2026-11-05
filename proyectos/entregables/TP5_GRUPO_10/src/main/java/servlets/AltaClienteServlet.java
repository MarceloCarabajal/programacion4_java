package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import entidades.Cliente;
import negocio.ClienteNegocio;

@WebServlet("/AltaClienteServlet")
public class AltaClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Solo letras (con tildes y ñ), permitiendo espacios entre palabras: "María José", "Río Negro".
    private static final String SOLO_LETRAS = "\\p{L}+(\\s\\p{L}+)*";
    // Letras, números y los signos comunes de una dirección: "Av. Libertador 456", "Ruta 8 km 52".
    private static final String LETRAS_Y_NUMEROS = "[\\p{L}0-9 .,°º/\\-]+";

    public AltaClienteServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        Cliente cliente = new Cliente();
        cliente.setDni(leer(request, "txtDni"));
        cliente.setCuil(leer(request, "txtCuil"));
        cliente.setNombre(leer(request, "txtNombre"));
        cliente.setApellido(leer(request, "txtApellido"));
        cliente.setSexo(leer(request, "txtSexo"));
        cliente.setFechaNacimiento(leer(request, "txtFechaNacimiento"));
        cliente.setDireccion(leer(request, "txtDireccion"));
        cliente.setNacionalidad(leer(request, "txtNacionalidad"));
        cliente.setLocalidad(leer(request, "txtLocalidad"));
        cliente.setProvincia(leer(request, "txtProvincia"));
        cliente.setCorreoElectronico(leer(request, "txtCorreo"));
        cliente.setTelefono(leer(request, "txtTelefono"));

        ClienteNegocio clienteNegocio = new ClienteNegocio();

        String error = validar(cliente, clienteNegocio);
        if (error != null) {
            request.setAttribute("mensajeError", error);
            request.getRequestDispatcher("AltaCliente.jsp").forward(request, response);
            return;
        }

        boolean insertado = clienteNegocio.insertar(cliente);

        if (insertado) {
            request.setAttribute("mensaje", "¡Cliente registrado con éxito!");
        } else {
            request.setAttribute("mensajeError", "Error: No se pudo registrar el cliente.");
        }

        request.getRequestDispatcher("AltaCliente.jsp").forward(request, response);
    }

    // Devuelve el mensaje del primer error encontrado, o null si todo está bien.
    private String validar(Cliente c, ClienteNegocio clienteNegocio) {
        if (c.getDni().isEmpty() || c.getCuil().isEmpty() || c.getNombre().isEmpty()
                || c.getApellido().isEmpty() || c.getSexo().isEmpty() || c.getFechaNacimiento().isEmpty()
                || c.getDireccion().isEmpty() || c.getNacionalidad().isEmpty() || c.getLocalidad().isEmpty()
                || c.getProvincia().isEmpty() || c.getCorreoElectronico().isEmpty() || c.getTelefono().isEmpty()) {
            return "Debe ingresar todos los campos para poder continuar.";
        }

        if (!c.getDni().matches("\\d{6,8}")) {
            return "El DNI debe ser numérico y tener entre 6 y 8 dígitos.";
        }
        if (!c.getCuil().matches("\\d{11}")) {
            return "El CUIL debe ser numérico y tener 11 dígitos.";
        }
        if (!c.getNombre().matches(SOLO_LETRAS)) {
            return "El nombre solo puede contener letras.";
        }
        if (!c.getApellido().matches(SOLO_LETRAS)) {
            return "El apellido solo puede contener letras.";
        }
        if (!c.getSexo().equals("M") && !c.getSexo().equals("F") && !c.getSexo().equals("Otro")) {
            return "Debe seleccionar un sexo válido (M, F u Otro).";
        }

        try {
            LocalDate fecha = LocalDate.parse(c.getFechaNacimiento());
            if (fecha.isAfter(LocalDate.now())) {
                return "La fecha de nacimiento no puede ser futura.";
            }
        } catch (DateTimeParseException e) {
            return "La fecha de nacimiento no es válida.";
        }

        if (!c.getDireccion().matches(LETRAS_Y_NUMEROS)) {
            return "La dirección solo puede contener letras y números.";
        }
        if (!c.getNacionalidad().matches(SOLO_LETRAS)) {
            return "La nacionalidad solo puede contener letras.";
        }
        if (!c.getLocalidad().matches(SOLO_LETRAS)) {
            return "La localidad solo puede contener letras.";
        }
        if (!c.getProvincia().matches(SOLO_LETRAS)) {
            return "La provincia solo puede contener letras.";
        }
        if (!c.getTelefono().matches("\\d{10}")) {
            return "El teléfono debe ser numérico y tener 10 dígitos.";
        }
        if (clienteNegocio.existeDni(c.getDni())) {
            return "Ya existe un cliente con ese DNI.";
        }
        if (clienteNegocio.existeCuil(c.getCuil())) {
            return "Ya existe un cliente con ese CUIL.";
        }

        return null;
    }
    
    private String leer(HttpServletRequest request, String nombre) {
        String valor = request.getParameter(nombre);
        return valor == null ? "" : valor.trim();
    }
}