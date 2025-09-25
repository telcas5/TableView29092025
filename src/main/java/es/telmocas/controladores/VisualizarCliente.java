package es.telmocas.controladores;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VisualizarCliente extends Application {

        @FXML
        private Button btnAniadir;

        @FXML
        private Button btnEliminar;

        @FXML
        private Button btnRestaurar;

        @FXML
        private DatePicker dateNacimiento;

        @FXML
        private TextField txtApellido;

        @FXML
        private TextField txtNombre;


    @Override
    public void start(Stage stage) throws Exception {

    }
}

