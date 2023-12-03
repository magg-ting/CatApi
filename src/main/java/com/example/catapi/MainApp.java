package com.example.catapi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);

        // Load BootstrapFX stylesheet as well as customized CSS stylesheet
        scene.getStylesheets().addAll(
                BootstrapFX.bootstrapFXStylesheet(),
                getClass().getResource("styles.css").toExternalForm());

        //Customize the icon & title on the title bar
        Image favicon = new Image(getClass().getResourceAsStream("favicon.png"));
        stage.getIcons().add(favicon);
        stage.setTitle("Random Cat Images");

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}