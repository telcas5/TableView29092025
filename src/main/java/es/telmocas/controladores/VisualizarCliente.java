package es.telmocas.controladores;

import es.telmocas.modelos.Persona;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Controlador de la vista "VisualizarCliente.fxml".
 * Gestiona la interacción con la tabla y los formularios para añadir, eliminar o restaurar personas.
 *
 * @author Telmo Castillo
 * @version 1.0
 */
public class VisualizarCliente {

    private static final Logger logger = Logger.getLogger(VisualizarCliente.class.getName());

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private DatePicker dateNacimiento;

    @FXML
    private Button btnAniadir;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnRestaurar;

    @FXML
    private TableView<Persona> tableView;

    @FXML
    private TableColumn<Persona, Integer> colId;

    @FXML
    private TableColumn<Persona, String> colNombre;

    @FXML
    private TableColumn<Persona, String> colApellido;

    @FXML
    private TableColumn<Persona, LocalDate> colCumpleanos;

    // Lista observable de personas
    private ObservableList<Persona> personas = FXCollections.observableArrayList();

    // Lista original para restaurar
    private final List<Persona> backupPersonas = new ArrayList<>();

    private int idCounter = 1;

    /**
     * Metodo de inicialización del controlador.
     * Configura la tabla y los eventos de los botones.
     */

    @FXML
    void btnEliminar(ActionEvent event) {
        ObservableList<Persona> seleccionadas = tableView.getSelectionModel().getSelectedItems();

        if (seleccionadas.isEmpty()) {
            showAlert("Atención", "No hay filas seleccionadas para eliminar");
            return;
        }

        // Eliminar las filas seleccionadas
        personas.removeAll(seleccionadas);
        logger.info("Filas eliminadas: " + seleccionadas.size());
    }

    @FXML
    void menuAyuda(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayuda");
        alert.setHeaderText("Información");
        alert.setContentText("Versión: 1.0\nCreado por: Telmo Castillo");
        alert.showAndWait();
    }

    @FXML
    void menuCerrar(ActionEvent event) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Cerrar aplicación");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Estás seguro que quieres cerrar la aplicación?");

        // Mostrar diálogo y esperar respuesta
        var result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Cerrar la ventana principal
            // Obtenemos la ventana desde un nodo cualquiera, por ejemplo el tableView
            tableView.getScene().getWindow().hide();
        }
        // Si cancela, no hacemos nada (el diálogo se cierra automáticamente)
    }



    @FXML
    public void initialize() {
        logger.info("Inicializando controlador VisualizarCliente");

        // Configurar columnas de la tabla
        colId.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject()
        );
        colNombre.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        colApellido.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getApellidos()));
        colCumpleanos.setCellValueFactory(cellData -> new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getCumpleanos()));

        // Asignar la lista observable a la tabla
        tableView.setItems(personas);

        // Configurar botones
        btnAniadir.setOnAction(e -> aniadirPersona());
        btnEliminar.setOnAction(e -> eliminarFilas());
        btnRestaurar.setOnAction(e -> restaurarTabla());
    }

    /**
     * Añade una nueva persona a la tabla.
     */
    private void aniadirPersona() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        LocalDate fecha = dateNacimiento.getValue();

        if (nombre.isEmpty() || apellido.isEmpty() || fecha == null) {
            showAlert("Error", "Todos los campos deben estar completos");
            return;
        }

        Persona persona = new Persona(idCounter++, nombre, apellido, fecha);
        personas.add(persona);
        backupPersonas.add(persona);

        // Limpiar campos
        txtNombre.clear();
        txtApellido.clear();
        dateNacimiento.setValue(null);

        logger.info("Persona añadida: " + persona);
    }

    /**
     * Elimina las filas seleccionadas de la tabla.
     */
    private void eliminarFilas() {
        ObservableList<Persona> seleccionadas = tableView.getSelectionModel().getSelectedItems();

        if (seleccionadas.isEmpty()) {
            showAlert("Atención", "No hay filas seleccionadas para eliminar");
            return;
        }

        personas.removeAll(seleccionadas);
        logger.info("Filas eliminadas: " + seleccionadas.size());
    }

    /**
     * Restaura la tabla a su estado original.
     */
    private void restaurarTabla() {
        personas.setAll(backupPersonas);
        logger.info("Tabla restaurada al estado original");
    }

    /**
     * Muestra un mensaje de alerta.
     *
     * @param titulo   Título de la alerta
     * @param mensaje  Contenido de la alerta
     */
    private void showAlert(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
