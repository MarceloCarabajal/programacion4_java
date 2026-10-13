package dao;

import entidad.Seguro;
import entidad.TipoSeguro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SeguroDAO {
	
	private static final String SQL_LISTAR =
			"SELECT s.idSeguro, s.descripcion AS descripcionSeguro, s.costoContratacion, s.costoAsegurado, "
			+ "t.idTipo, t.descripcion AS descripcionTipo "
			+ "FROM seguros s INNER JOIN tipoSeguros t ON s.idTipo = t.idTipo";

	public List<Seguro> listarTodos() {
		List<Seguro> lista = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conexion = Conexion.obtenerConexion();
			// DATO : Se pueden concatenar consultas
			// DATO :Se pueden concatenar consultas
			ps = conexion.prepareStatement(SQL_LISTAR + " ORDER BY s.idSeguro");
			rs = ps.executeQuery();

			while (rs.next()) {
				lista.add(armarSeguro(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion(conexion, ps, rs);
		}

		return lista;
	}

	public List<Seguro> listarPorTipo(int idTipo) {
		List<Seguro> lista = new ArrayList<>();
		Connection conexion = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conexion = Conexion.obtenerConexion();
			ps = conexion.prepareStatement(SQL_LISTAR + " WHERE s.idTipo = ? ORDER BY s.idSeguro");
			ps.setInt(1, idTipo);
			rs = ps.executeQuery();

			while (rs.next()) {
				lista.add(armarSeguro(rs));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Conexion.cerrarConexion(conexion, ps, rs);
		}

		return lista;
	}

	// Convierte la fila actual del ResultSet en un objeto Seguro con su TipoSeguro adentro.
	private Seguro armarSeguro(ResultSet rs) throws SQLException {
		TipoSeguro tipo = new TipoSeguro();
		tipo.setIdTipo(rs.getInt("idTipo"));
		tipo.setDescripcion(rs.getString("descripcionTipo"));

		Seguro seguro = new Seguro();
		seguro.setIdSeguro(rs.getInt("idSeguro"));
		seguro.setDescripcion(rs.getString("descripcionSeguro"));
		seguro.setTipoSeguro(tipo);
		seguro.setCostoContratacion(rs.getDouble("costoContratacion"));
		seguro.setCostoAsegurado(rs.getDouble("costoAsegurado"));
		return seguro;
	}

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
