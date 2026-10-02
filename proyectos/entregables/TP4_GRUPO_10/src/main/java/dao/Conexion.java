package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion {
    private static final String HOST = "jdbc:mysql://localhost:3306/";
    private static final String BD = "SegurosGroup?useSSL=false";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(HOST + BD, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver JDBC no encontrado.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos.");
            e.printStackTrace();
        }
        return conexion;
    }
    
    public static void cerrarConexion(Connection conexion, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (conexion != null) conexion.close();
        } catch (SQLException e) {
            System.err.println("Error al cerrar los recursos de la base de datos.");
            e.printStackTrace();
        }
    }
}