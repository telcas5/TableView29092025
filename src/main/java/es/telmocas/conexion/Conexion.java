package es.telmocas.conexion;

import es.telmocas.App;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Conexion {


    private static Connection conexion;
    private static Properties props = new Properties();
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    // Ruta del archivo properties
    private static final String CONFIG_FILE = "configuration.properties";

    /**
     * Carga las propiedades desde el archivo .properties
     */

    private static void cargarConfiguracion() {
        try (InputStream input = Conexion.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (input == null) {
                logger.error("No se ha encontrado el archivo de configuracion");
            }
            props.load(input);
        } catch (IOException e) {
            logger.error("No se ha podido entrar en el archivo de configuracion");
        }
    }

    /**
     * Método para obtener la conexión a la base de datos.
     * Usa singleton para reutilizar la conexión si ya existe.
     */
    public static Connection getConnection()  {
        try {
            if (conexion == null || conexion.isClosed()) {
                cargarConfiguracion();  // carga las propiedades

                String url = props.getProperty("database.url");
                String user = props.getProperty("database.username");
                String password = props.getProperty("database.password");

                try {
                    conexion = DriverManager.getConnection(url, user, password);
                    logger.info("Ha entrado en la base de datos.");
                } catch (SQLException e) {
                    logger.error("No se ha podido entrar en la base de datos");
                }

            }
        } catch (SQLException e) {
            logger.error("No se ha podido hacer la conexion con la base de datos");
        }
        return conexion;
    }


}