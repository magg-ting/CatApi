/**
 * This is the controller class for the wiki-view.
 */
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

    /**
     * This method is called when user clicks the Back button to return to the details-view of the cat breed
     * @param event: onclick event
     * @throws IOException
     */
    public void viewDetails(ActionEvent event) throws IOException{
        super.viewDetails(event, cat);
    }

    /**
     * This method is called when user clicks the Search Again button to return to the main-view
     * @param event: onclick event
     * @throws IOException
     */
    public void home(ActionEvent event) throws IOException {
        super.home(event);
    }

    /**
     * This method is called to initialize the UI when the wiki-view is first loaded.
     * We do not use the initialize() method from the Initializable interface directly to ensure that the CatData & wikiURL are passed to the WikiController when we initialize the UI.
     */
    public void initializeUI(){
        WebEngine engine = webView.getEngine();
        engine.load(wikiUrl);
        mainBtn.getStyleClass().setAll("btn", "btn-success");
        backBtn.getStyleClass().setAll("btn", "btn-primary");
    }
}
