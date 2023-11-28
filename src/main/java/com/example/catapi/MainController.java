package com.example.catapi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ImageView resultImg;
    @FXML
    private ComboBox breedList;
    @FXML
    private Button searchBtn;
    @FXML
    private Button nextBtn;

    public void viewDetails(ActionEvent event) throws IOException{

    }
    @Override
    public void initialize(URL url, ResourceBundle rb){

    }
}