module es.telmocas {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.slf4j;
    requires jdk.compiler;
    requires java.desktop;
    requires java.logging;
    requires java.sql;

    opens es.telmocas.controladores to javafx.fxml;
    opens es.telmocas.modelos to javafx.base;

    opens es.telmocas to javafx.fxml;
    exports es.telmocas;
}