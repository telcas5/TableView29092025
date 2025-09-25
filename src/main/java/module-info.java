module es.telmocas {
    requires javafx.controls;
    requires javafx.fxml;

    opens es.telmocas to javafx.fxml;
    exports es.telmocas;
}
