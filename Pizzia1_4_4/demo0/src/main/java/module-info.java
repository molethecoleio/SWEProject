module pizza.project.demo0 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.desktop;
    requires java.logging;

    opens pizza.project.demo0 to javafx.fxml;
    exports pizza.project.demo0;
}