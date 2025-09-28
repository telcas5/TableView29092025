module es.telmocas {
    // Dependencias de JavaFX
    requires javafx.controls;
    requires javafx.fxml;

    // Para usar logging con java.util.logging o SLF4J (si lo llegas a integrar)
    requires java.logging;
    requires org.slf4j;

    // Permite que JavaFX acceda a las clases del paquete indicado
    opens es.telmocas to javafx.fxml;
    opens es.telmocas.modelos to javafx.base;

    // Exporta los paquetes principales para que puedan usarse desde fuera del módulo
    exports es.telmocas;
    exports es.telmocas.modelos;
}