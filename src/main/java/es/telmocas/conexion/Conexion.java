package es.telmocas.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/cliente";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private static Connection conexion;

    /**
     * Metodo para obtener la conexión a la base de datos.
     * Usa un singleton para reutilizar la conexión si ya existe.
     */
    public static Connection getConnection() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return conexion;


}
}