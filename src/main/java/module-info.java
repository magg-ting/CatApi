module com.example.catapi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires org.json;

    opens com.example.catapi to javafx.fxml;
    exports com.example.catapi;
}