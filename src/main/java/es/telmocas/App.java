package es.telmocas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Clase principal de la aplicación JavaFX.
 * <p>
 * Esta clase inicializa la aplicación cargando la interfaz gráfica
 * desde un archivo FXML y aplicando un estilo CSS opcional.
 * También configura la ventana principal (Stage) y
 * gestiona los posibles errores en la carga de recursos.
 * </p>
 *
 * Ejemplo de uso:
 * <pre>
 *     public static void main(String[] args) {
 *         App.main(args);
 *     }
 * </pre>
 *
 * @author Telmo Castillo
 * @version 1.0
 */
public class App extends Application {

    /** Logger para registrar información, advertencias y errores. */
    private static final Logger logger = LoggerFactory.getLogger(App.class);

    /** Escena principal de la aplicación. */
    private static Scene scene;

    /**
     * Método principal de inicio de la aplicación JavaFX.
     * <p>
     * Se encarga de:
     * <ul>
     *     <li>Cargar el archivo FXML que define la interfaz gráfica.</li>
     *     <li>Aplicar el archivo CSS de estilos (si existe).</li>
     *     <li>Configurar y mostrar la ventana principal.</li>
     * </ul>
     * </p>
     *
     * @param stage la ventana principal de la aplicación.
     */
    @Override
    public void start(Stage stage) {
        try {
            logger.info("Iniciando la aplicación...");

            // Cargar el archivo FXML de la interfaz
            FXMLLoader loader = new FXMLLoader(
                    Objects.requireNonNull(getClass().getResource("/fxml/visualizarCliente.fxml"),
                            "No se encontró el archivo FXML")
            );

            scene = new Scene(loader.load());
            logger.info("Archivo FXML cargado correctamente.");

            // Cargar y aplicar el archivo CSS si está disponible
            URL archivoCSS = getClass().getResource("/css/estilo.css");
            if (archivoCSS != null) {
                scene.getStylesheets().add(archivoCSS.toExternalForm());
                logger.info("Estilo CSS aplicado desde: " + archivoCSS);
            } else {
                logger.warn("No se encontró el archivo CSS, se usará el estilo por defecto.");
            }

            // Configurar y mostrar la ventana principal
            stage.setTitle("TableView25092025");
            stage.setScene(scene);
            stage.show();
            logger.info("Aplicación iniciada correctamente y ventana mostrada.");

        } catch (IOException e) {
            logger.warn("Error al cargar la interfaz FXML.", e);
        } catch (Exception e) {
            logger.warn("Error inesperado durante el inicio de la aplicación.", e);
        }
    }

    /**
     * Método de entrada principal.
     * <p>
     * Lanza la aplicación JavaFX inicializando la interfaz.
     * </p>
     *
     * @param args argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        logger.info("Lanzando aplicación JavaFX...");
        launch();
    }
}
