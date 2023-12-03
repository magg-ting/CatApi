package com.example.catapi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;


public class DetailsController extends SceneController{
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
    private Hyperlink wikiUrl;
    @FXML
    private Button backBtn;
    @FXML
    private Label originLabel;
    @FXML
    private Label weightLabel;
    @FXML
    private Label lifespanLabel;
    @FXML
    private Label descriptionLabel;
    @FXML
    private Label wikiUrlLabel;

    private CatData cat;
    public void setCatData(CatData cat){
        this.cat = cat;
    }
    private String breedWikiUrl;

    public void home(ActionEvent event) throws IOException {
        super.home(event);
    }

    public void openWiki(ActionEvent event) throws IOException {
        super.openWiki(event, cat, breedWikiUrl);
    }

    public void initializeUI(){
        String imgId = cat.getImgId();
        String imgUrl = cat.getImgUrl();
        Breed breed = CatApiUtil.parseBreedFromImg(imgId);
        name.setText(" " + breed.getName() + " ");
        imageView.setImage(new Image(imgUrl));
        temperament.setText(breed.getTemperament());
        origin.setText(breed.getOrigin());
        weight.setText(breed.getWeight());
        lifespan.setText(breed.getLifespan());
        description.setText(breed.getDescription());
        this.breedWikiUrl = breed.getWikiUrl();
        wikiUrl.setText(breedWikiUrl);

        //Add style classes to customize the UI
        name.getStyleClass().setAll("h1");
        temperament.getStyleClass().setAll("h2");
        backBtn.getStyleClass().setAll("btn", "btn-success");
        originLabel.getStyleClass().setAll("strong");
        weightLabel.getStyleClass().setAll("strong");
        lifespanLabel.getStyleClass().setAll("strong");
        descriptionLabel.getStyleClass().setAll("strong");
        wikiUrlLabel.getStyleClass().setAll("strong");
        wikiUrl.getStyleClass().setAll("a", "text-primary");
    }
}
