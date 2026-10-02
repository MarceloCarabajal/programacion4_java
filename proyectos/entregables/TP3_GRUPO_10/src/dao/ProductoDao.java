package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import entidad.Producto;
import entidad.Categoria;
import java.util.ArrayList;

public class ProductoDao {
	private String host = "jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String pass = "root";
	private String dbName = "bdInventario";

	public int agregarProducto(Producto producto) {
		String query = "Insert into productos(codigo, nombre, precio, stock, idCategoria) values ('" + producto.getCodigo() + "',' "
				+ producto.getNombre() + "', '"+producto.getPrecio() + "',' " + producto.getStock() + "',' " + producto.getIdCategoria()+ "')";
		Connection cn = null;
		int filas = 0;

		try {
			cn = DriverManager.getConnection(host+dbName,user, pass);
			Statement st = cn.createStatement();
			filas = st.executeUpdate(query);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return filas;
		}

	// El alta que pide el punto D. El SP declara los parametros tipados, asique el precio
	// viaja como decimal y el stock como entero, no como texto entre comillas
	public boolean agregarProductoConSp(Producto producto) {
		boolean estado = false;
		Connection cn = Conexion.obtenerConexion();
		String llamada = "{ call sp_AgregarProducto(?, ?, ?, ?, ?) }";

		try {
			CallableStatement cst = cn.prepareCall(llamada);
			cst.setString(1, producto.getCodigo());
			cst.setString(2, producto.getNombre());
			cst.setDouble(3, producto.getPrecio());
			cst.setInt(4, producto.getStock());
			cst.setInt(5, producto.getIdCategoria());

			if (cst.executeUpdate() > 0) {
				estado = true;
			}
			cn.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return estado;
	}
	
	// LISTADO
    public List<Producto> obtenerTodos() {
        List<Producto> lista = new ArrayList<>();
        Connection cn = Conexion.obtenerConexion();
        String query = "SELECT p.codigo, p.nombre, p.precio, p.stock, c.idCategoria, c.nombre AS nombreCategoria " +
                       "FROM productos p INNER JOIN categorias c ON p.idCategoria = c.idCategoria";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Producto prod = new Producto();
                prod.setCodigo(rs.getString("codigo"));
                prod.setNombre(rs.getString("nombre"));
                prod.setPrecio(rs.getDouble("precio"));
                prod.setStock(rs.getInt("stock"));

                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("idCategoria"));
                cat.setNombre(rs.getString("nombreCategoria"));

                prod.setIdCategoria(rs.getInt("idCategoria"));
                lista.add(prod);
            }
            
            rs.close();
            st.close();
            cn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return lista;
    }
    
    public boolean eliminarProducto(Producto producto) {
        Connection cn = Conexion.obtenerConexion();
        String query = "DELETE FROM Productos WHERE Codigo = ?";
        boolean estado = false;

        try {
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, producto.getCodigo());

            if (pst.executeUpdate() > 0) {
                estado = true;
            }
            cn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return estado;
    }

    public boolean modificarProducto(Producto producto) {
        Connection cn = Conexion.obtenerConexion();
        String query = "UPDATE Productos SET Nombre = ?, Precio = ?, Stock = ?, IdCategoria = ? WHERE Codigo = ?";
        boolean estado = false;

        try {
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, producto.getNombre());
            pst.setDouble(2, producto.getPrecio());
            pst.setInt(3, producto.getStock());
            pst.setInt(4, producto.getIdCategoria());
            pst.setString(5, producto.getCodigo());

            if (pst.executeUpdate() > 0) {
                estado = true;
            }
            cn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return estado;
    }
}
