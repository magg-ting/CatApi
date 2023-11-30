package com.example.catapi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ImageView resultImg;
    @FXML
    private ComboBox<Breed> breedList;
    @FXML
    private Button searchBtn;
    @FXML
    private Button nextBtn;

    public String getSelectedBreedId(){
        Breed selectedBreed = breedList.getSelectionModel().getSelectedItem();
        if(selectedBreed != null && !selectedBreed.getBreedId().equals("ANY")){
            System.out.println(selectedBreed.getBreedId());
            return selectedBreed.getBreedId();
        }
        else{
            System.out.println("null");
            return "";
        }
    }

    public void search(){
        String base_url = "https://api.thecatapi.com/v1/images/search";
        String params = "";
        String selectedBreedId = getSelectedBreedId();
        if(!selectedBreedId.isEmpty()){
            params = "breed_ids=" + selectedBreedId;
        }
        try{
            String response = CatApiUtil.fetchJsonData(base_url, params);
            CatData cat = CatApiUtil.parseImg(response);
            Image image = new Image(cat.getImgUrl());
            resultImg.setImage(image);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void viewDetails(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("details-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Breed Details");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        //Create a dummy Breed object to represent a wildcard search
        Breed anyBreed = new Breed();
        anyBreed.setBreedId("ANY");
        anyBreed.setName("ANY");

        //Get all breeds available from the API
        List<Breed> allBreeds = CatApiUtil.parseAllBreeds();

        //Add the dummy Breed to the beginning of the list
        allBreeds.add(0, anyBreed);

        //Add the expanded list to the ComboBox
        breedList.getItems().addAll(allBreeds);
        breedList.setCellFactory(param -> new ListCell<Breed>(){
            @Override
            protected void updateItem(Breed breed, boolean empty){
                super.updateItem(breed, empty);
                if(empty || breed == null){
                    setText(null);
                }
                else{
                    setText(breed.getName());
                }
            }
        });

    }
}