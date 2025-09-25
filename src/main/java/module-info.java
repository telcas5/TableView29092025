module es.telmocas {
    requires javafx.controls;
    requires javafx.fxml;

    opens es.telmocas to javafx.fxml;
    exports es.telmocas;
    exports es.telmocas.controladores;
    opens es.telmocas.controladores to javafx.fxml;
}
