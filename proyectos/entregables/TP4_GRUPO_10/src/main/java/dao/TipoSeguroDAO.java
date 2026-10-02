package dao;

import entidad.TipoSeguro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TipoSeguroDAO {
	public List obtenerTiposSeguro() {
		List lista = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT idTipo, descripcion FROM tipos_seguros";

        try {
            conexion = Conexion.obtenerConexion();
            ps = conexion.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                TipoSeguro ts = new TipoSeguro();
                ts.setIdTipo(rs.getInt("idTipo"));
                ts.setDescripcion(rs.getString("descripcion"));
                lista.add(ts);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexion.cerrarConexion(conexion, ps, rs);
        }

        return lista;
    }
}