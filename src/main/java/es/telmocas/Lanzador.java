package es.telmocas;

/**
 * Clase lanzadora de la aplicación.
 * <p>
 * Esta clase existe como punto de entrada alternativo para iniciar
 * la aplicación JavaFX {@link App}. En algunos entornos de ejecución
 * (por ejemplo, al empaquetar con Maven o desde ciertos IDEs),
 * es necesario tener una clase con un método {@code main} separado
 * de la que extiende {@link javafx.application.Application}.
 * </p>
 *
 * Ejemplo de uso:
 * <pre>
 *     java es.telmocas.Lanzador
 * </pre>
 *
 * @author Telmo Castillo
 * @version 1.0
 */
public class Lanzador {

    /**
     * Método principal de ejecución.
     * <p>
     * Llama al método {@link App#main(String[])} para lanzar
     * la aplicación JavaFX.
     * </p>
     *
     * @param args argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        App.main(args);
    }
}