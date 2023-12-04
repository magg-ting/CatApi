/**
 * This is the controller class for the main-view.
 */
package com.example.catapi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController extends SceneController implements Initializable {
    @FXML
    private Label heading;
    @FXML
    private Label selectLabel;
    @FXML
    private Button searchBtn;
    @FXML
    private ImageView resultImg;
    @FXML
    private ComboBox<Breed> breedList;
    @FXML
    private Button nextBtn;
    private static CatData cat;

    /**
     * This method gets the id of the breed selected by the user
     * @return String: the id of the selected breed name
     */
    public String getSelectedBreedId(){
        Breed selectedBreed = breedList.getSelectionModel().getSelectedItem();
        if(selectedBreed != null && !selectedBreed.getBreedId().equals("ANY")){
            System.out.println("Selected Breed ID: " + selectedBreed.getBreedId());
            return selectedBreed.getBreedId();
        }
        else{
            return "";
        }
    }

    /**
     * This method is called when user clicks the search button to initiate a call to the CAT API
     */
    public void search(){
        String base_url = "https://api.thecatapi.com/v1/images/search";
        String params = "&has_breeds=1";    //limit the result to those cats with breed info
        String selectedBreedId = getSelectedBreedId();
        // Note: if user has not selected any breed from the combobox, the API call will return a random cat image of any breeds by default
        if(!selectedBreedId.isEmpty()){
            params += "&breed_ids=" + selectedBreedId;
        }

        try{
            String response = CatApiUtil.fetchJsonData(base_url, params);
            CatData displayedCat = CatApiUtil.parseImg(response);
            // Show the result cat image and a Learn More button
            Image image = new Image(displayedCat.getImgUrl());
            resultImg.setImage(image);
            nextBtn.setVisible(true);

            // Assign the cat details of the image to the cat variable
            cat = displayedCat;
            System.out.println("Selected Image ID: " + cat.getImgId());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * This method is called when user clicks the Learn More button to view details of the cat breed
     * @param event: onclick event
     * @throws IOException
     */
    public void viewDetails(ActionEvent event) throws IOException{
        super.viewDetails(event, cat);
    }

    /**
     * This overrides the initialize method to populate the combobox and set style classes when the view is first loaded.
     */
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

        //Add style classes to customize the UI
        heading.getStyleClass().setAll("h1");
        selectLabel.getStyleClass().setAll("label-lg");
        searchBtn.getStyleClass().setAll("btn", "btn-success");
        nextBtn.getStyleClass().setAll("btn", "btn-primary");
    }
}