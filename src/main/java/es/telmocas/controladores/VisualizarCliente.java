package es.telmocas.controladores;

import es.telmocas.dao.ClienteDAO;
import es.telmocas.modelos.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.logging.Logger;

/**
 * Controlador de la vista VisualizarCliente.fxml.
 *
 * Este controlador gestiona la interacción entre la interfaz gráfica (JavaFX) y la base de datos
 * para la entidad Persona. Permite:
 *  - Visualizar clientes almacenados en la base de datos
 *  - Añadir nuevos clientes mediante un formulario
 *  - Eliminar clientes seleccionados de la tabla y de la base de datos
 *  - Refrescar/restaurar la tabla para mantenerla sincronizada con la base de datos
 *
 * Autor: Telmo Castillo
 * Versión: 1.0
 */
public class VisualizarCliente {

    private static final Logger logger = Logger.getLogger(VisualizarCliente.class.getName());

    // Componentes FXML
    @FXML private TextField txtNombre;
    @FXML private TextField txtApellido;
    @FXML private DatePicker dateNacimiento;
    @FXML private Button btnAniadir;
    @FXML private Button btnEliminar;
    @FXML private Button btnRestaurar;
    @FXML private TableView<Persona> tableView;
    @FXML private TableColumn<Persona, Integer> colId;
    @FXML private TableColumn<Persona, String> colNombre;
    @FXML private TableColumn<Persona, String> colApellido;
    @FXML private TableColumn<Persona, LocalDate> colCumpleanos;

    /**
     * Lista observable que mantiene los clientes mostrados en la tabla.
     */
    private ObservableList<Persona> personas = FXCollections.observableArrayList();

    /**
     * Objeto DAO responsable de las operaciones en la base de datos.
     */
    private final ClienteDAO clienteDAO = new ClienteDAO();

    /**
     * Inicializa el controlador al cargar la vista FXML.
     * Configura las columnas de la tabla, carga los datos de la base de datos
     * y asigna las acciones a los botones.
     */
    @FXML
    public void initialize() {
        logger.info("Inicializando controlador VisualizarCliente");

        // Configuración de columnas
        colId.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getId()).asObject()
        );
        colNombre.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        colApellido.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getApellido()));
        colCumpleanos.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleObjectProperty<>(cellData.getValue().getCumpleanos()));

        // Cargar datos
        refrescarTabla();

        // Acciones de botones
        btnAniadir.setOnAction(e -> aniadirPersona());
        btnEliminar.setOnAction(e -> eliminarPersona());
        btnRestaurar.setOnAction(e -> refrescarTabla());
    }

    /**
     * Añade una nueva persona a la base de datos y actualiza la tabla.
     * Valida que los campos no estén vacíos.
     */
    private void aniadirPersona() {
        String nombre = txtNombre.getText().trim();
        String apellido = txtApellido.getText().trim();
        LocalDate fecha = dateNacimiento.getValue();

        if (nombre.isEmpty() || apellido.isEmpty() || fecha == null) {
            showAlert("Error", "Todos los campos deben estar completos");
            return;
        }

        Persona persona = new Persona(0, nombre, apellido, fecha);
        clienteDAO.insertarCliente(persona);

        refrescarTabla();

        txtNombre.clear();
        txtApellido.clear();
        dateNacimiento.setValue(null);

        logger.info("Cliente añadido: " + persona);
    }

    /**
     * Elimina el cliente seleccionado en la tabla de la base de datos.
     * Si no hay ninguno seleccionado, muestra una alerta.
     */
    private void eliminarPersona() {
        Persona seleccionada = tableView.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            showAlert("Atención", "Debe seleccionar un cliente para eliminar");
            return;
        }

        clienteDAO.eliminarCliente(seleccionada.getId());
        refrescarTabla();

        logger.info("Cliente eliminado: " + seleccionada);
    }

    /**
     * Refresca la tabla con los datos actuales de la base de datos.
     */
    private void refrescarTabla() {
        personas.setAll(clienteDAO.obtenerTodos());
        tableView.setItems(personas);
        logger.info("Tabla actualizada desde la base de datos");
    }

    /**
     * Muestra una alerta informativa.
     *
     * @param titulo   título de la alerta
     * @param mensaje  contenido de la alerta
     */
    private void showAlert(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra información de ayuda sobre la aplicación.
     */
    @FXML
    void menuAyuda() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ayuda");
        alert.setHeaderText("Información");
        alert.setContentText("Versión: 1.0\nCreado por: Telmo Castillo");
        alert.showAndWait();
    }

    /**
     * Pregunta al usuario si desea cerrar la aplicación y la cierra si acepta.
     */
    @FXML
    void menuCerrar() {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Cerrar aplicación");
        confirm.setHeaderText(null);
        confirm.setContentText("¿Estás seguro que quieres cerrar la aplicación?");

        var result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            tableView.getScene().getWindow().hide();
        }
    }
}
