package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import entidad.Categoria;

public class CategoriaDao {

    //	ALTA DIRECTA
	public boolean agregarCategoria(Categoria categoria) {
        boolean estado = false;
        Connection cn = Conexion.obtenerConexion();
        String query = "INSERT INTO Categorias (Nombre) VALUES (?)";

        try {
            /*
        	PreparedStatement pst = cn.prepareStatement(query);
            pst.setString(1, categoria.getNombre());

            if (pst.executeUpdate() > 0) {
                estado = true;
            }
            cn.close();
            */
        	
        	PreparedStatement pst = cn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
        	pst.setString(1, categoria.getNombre());
        	
        	if (pst.executeUpdate() > 0) {
                ResultSet rs = pst.getGeneratedKeys();

                if (rs.next()) {
                    categoria.setIdCategoria(rs.getInt(1));
                    estado = true;
                }
            }
        	
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estado;
    }

	//	BAJA
	
	public boolean eliminarCategoria(Categoria categoria) {
        Connection cn = Conexion.obtenerConexion();
        String query = "DELETE FROM Categorias WHERE IdCategoria = ?";
        boolean estado = false;

        try {
            PreparedStatement pst = cn.prepareStatement(query);
            pst.setInt(1, categoria.getIdCategoria());

            if (pst.executeUpdate() > 0) {
                estado = true;
            }
            cn.close();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estado;
    }
	
	
	//	MODIFICACION
	public boolean modificarCategoria(Categoria categoria) {
	    Connection cn = Conexion.obtenerConexion();
	    String query = "UPDATE Categorias SET Nombre = ? WHERE IdCategoria = ?";
	    boolean estado = false;

	    try {
	        PreparedStatement pst = cn.prepareStatement(query);

	        pst.setString(1, categoria.getNombre());
	        pst.setInt(2, categoria.getIdCategoria());

	        if (pst.executeUpdate() > 0) {
	            estado = true;
	        }
	        cn.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return estado;
	}
	
	//	LISTADO
    public List<Categoria> obtenerTodas() {
        List<Categoria> lista = new ArrayList<>();
        Connection cn = Conexion.obtenerConexion();
        String query = "SELECT IdCategoria, Nombre FROM Categorias";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                Categoria cat = new Categoria();
                cat.setIdCategoria(rs.getInt("IdCategoria"));
                cat.setNombre(rs.getString("Nombre"));
                lista.add(cat);
            }
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
