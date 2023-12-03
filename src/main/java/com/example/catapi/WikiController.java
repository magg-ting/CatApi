package com.example.catapi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.IOException;

public class WikiController extends SceneController{
    @FXML
    private WebView webView;
    @FXML
    private Button backBtn;
    @FXML
    private Button mainBtn;

    private CatData cat;
    private String wikiUrl;
    public void setCatData(CatData cat){
        this.cat = cat;
    }
    public void setWikiUrl(String wikiUrl){
        this.wikiUrl = wikiUrl;
    }

    public void viewDetails(ActionEvent event) throws IOException{
        super.viewDetails(event, cat);
    }

    public void home(ActionEvent event) throws IOException {
        super.home(event);
    }

    public void initializeUI(){
        WebEngine engine = webView.getEngine();
        engine.load(wikiUrl);
        mainBtn.getStyleClass().setAll("btn", "btn-success");
        backBtn.getStyleClass().setAll("btn", "btn-primary");
    }
}
