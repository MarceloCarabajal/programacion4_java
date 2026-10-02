package dao;

import entidad.Seguro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SeguroDAO {
	// listarTodos

	public int obtenerProximoId() {
		int proximoId = 1;
		Connection conexion = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "SELECT IFNULL(MAX(idSeguro), 0) + 1 AS proximoId FROM seguros";

		try {
			conexion = Conexion.obtenerConexion();
			ps = conexion.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				proximoId = rs.getInt("proximoId");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion(conexion, ps, rs);
		}

		return proximoId;
	}

	public boolean insertar(Seguro seguro) {
		boolean insertado = false;
		Connection conexion = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "INSERT INTO seguros (descripcion, idTipo, costoContratacion, costoAsegurado) VALUES (?, ?, ?, ?)";

		try {
			conexion = Conexion.obtenerConexion();
			ps = conexion.prepareStatement(sql);
			ps.setString(1, seguro.getDescripcion());
			ps.setInt(2, seguro.getTipoSeguro().getIdTipo());
			ps.setDouble(3, seguro.getCostoContratacion());
			ps.setDouble(4, seguro.getCostoAsegurado());

			if (ps.executeUpdate() > 0) {
				insertado = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion(conexion, ps, rs);
		}

		return insertado;
	}
}
