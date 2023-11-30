package com.example.catapi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        stage.setTitle("Random Cat Images");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        try {
            String response = CatApiUtil.fetchJsonData("https://api.thecatapi.com/v1/images/search", "breed_ids=aege");
            CatApiUtil.parseImg(response);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        launch();
    }
}