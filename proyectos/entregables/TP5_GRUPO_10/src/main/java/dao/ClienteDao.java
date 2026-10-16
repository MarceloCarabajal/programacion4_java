package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import entidades.Cliente;

public class ClienteDao implements IClienteDao {

    public List<Cliente> obtenerTodos() {
        List<Cliente> listaClientes = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT id_cliente, dni, cuil, nombre, apellido, sexo, fecha_nacimiento, direccion, nacionalidad, localidad, provincia, correo_electronico, telefono FROM clientes";

        try {
            conexion = Conexion.obtenerConexion();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setDni(rs.getString("dni"));
                c.setCuil(rs.getString("cuil"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setSexo(rs.getString("sexo"));
                c.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                c.setDireccion(rs.getString("direccion"));
                c.setNacionalidad(rs.getString("nacionalidad"));
                c.setLocalidad(rs.getString("localidad"));
                c.setProvincia(rs.getString("provincia"));
                c.setCorreoElectronico(rs.getString("correo_electronico"));
                c.setTelefono(rs.getString("telefono"));

                listaClientes.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion(conexion, ps, rs);
        }

        return listaClientes;
    }


    public boolean agregarCliente(Cliente cliente) {
        Connection conexion = null;
        PreparedStatement ps = null;
        boolean insertado = false;

        String sql = "INSERT INTO clientes (dni, cuil, nombre, apellido, sexo, fecha_nacimiento, direccion, nacionalidad, localidad, provincia, correo_electronico, telefono) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            conexion = Conexion.obtenerConexion();
            ps = conexion.prepareStatement(sql);
            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getCuil());
            ps.setString(3, cliente.getNombre());
            ps.setString(4, cliente.getApellido());
            ps.setString(5, cliente.getSexo());
            ps.setString(6, cliente.getFechaNacimiento());
            ps.setString(7, cliente.getDireccion());
            ps.setString(8, cliente.getNacionalidad());
            ps.setString(9, cliente.getLocalidad());
            ps.setString(10, cliente.getProvincia());
            ps.setString(11, cliente.getCorreoElectronico());
            ps.setString(12, cliente.getTelefono());

            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas > 0) {
                insertado = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion(conexion, ps, null);
        }

        return insertado;
    }
}