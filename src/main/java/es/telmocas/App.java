package es.telmocas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loaded = new FXMLLoader(getClass().getResource("/fxml/visualizarCliente.fxml"));
        Scene scene = new Scene(loaded.load());
        var archivoCSS = getClass().getResource("/css/estilo.css");
        scene.getStylesheets().add(archivoCSS.toExternalForm());

        stage.setTitle("TableView25092025");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}