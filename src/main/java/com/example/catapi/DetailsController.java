package com.example.catapi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DetailsController{
    @FXML
    private Label name;
    @FXML
    private ImageView imageView;
    @FXML
    private Label temperament;
    @FXML
    private Label origin;
    @FXML
    private Label weight;
    @FXML
    private Label lifespan;
    @FXML
    private Label description;
    @FXML
    private Label wikiUrl;
//    @FXML
//    private GridPane gridPane;

    private CatData cat;
    public void setCatData(CatData cat){
        this.cat = cat;
    }

//    @Override
//    public void start(Stage stage) throws IOException{
//        RowConstraints rowConstraints = new RowConstraints();
//        rowConstraints.setVgrow(Priority.ALWAYS);
//
//        double descriptionLabelHeight = description.getBoundsInParent().getHeight();
//        gridPane.getRowConstraints().get(3).setMinHeight(descriptionLabelHeight + 10);
//
//        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("details-view.fxml"));
//        Parent root = fxmlLoader.load();
//
//        DetailsController detailsController = fxmlLoader.getController();
//        detailsController.setCatData(cat);
//        detailsController.initializeUI();
//
//        Scene scene = new Scene(root, 400, 600);
//        stage.setTitle("Breed Details");
//        stage.setScene(scene);
//        stage.show();
//    }

    public void back(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        //gets the Stage info
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Random Cat Images");
        stage.setScene(scene);
        stage.show();
    }

    public void initializeUI(){
        String imgId = cat.getImgId();
        String imgUrl = cat.getImgUrl();
        Breed breed = CatApiUtil.parseBreedFromImg(imgId);
        name.setText(breed.getName());
        imageView.setImage(new Image(imgUrl));
        temperament.setText(breed.getTemperament());
        origin.setText(breed.getOrigin());
        weight.setText(breed.getWeight());
        lifespan.setText(breed.getLifespan());
        description.setText(breed.getDescription());
        wikiUrl.setText(breed.getWikiUrl());
    }
}
