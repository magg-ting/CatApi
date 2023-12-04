/**
 * This abstract class defines the methods for switching among different views.
 */
package com.example.catapi;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

public abstract class SceneController {
    /**
     * This method is called to redirect user to the main-view
     * @param event: onclick event
     * @throws IOException
     */
    public void home(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 600);
        // Load BootstrapFX stylesheet as well as customized CSS stylesheet
        scene.getStylesheets().addAll(
                BootstrapFX.bootstrapFXStylesheet(),
                getClass().getResource("styles.css").toExternalForm());

        //gets the Stage info
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Random Cat Images");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is called to redirect user to the details-view of a particular cat breed
     * @param event: onclick event
     * @param cat: CatData of the cat image shown
     * @throws IOException
     */
    public void viewDetails(ActionEvent event, CatData cat) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("details-view.fxml"));
        Parent root = fxmlLoader.load();

        DetailsController detailsController = fxmlLoader.getController();
        detailsController.setCatData(cat);
        detailsController.initializeUI();

        Scene scene = new Scene(root, 400, 600);
        // Load BootstrapFX stylesheet as well as customized CSS stylesheet
        scene.getStylesheets().addAll(
                BootstrapFX.bootstrapFXStylesheet(),
                getClass().getResource("styles.css").toExternalForm());

        //gets the stage info
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Breed Details");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * This method is called to redirect user to the wiki-view of a particular cat breed
     * @param event: onclick event
     * @param cat: CatData of the cat image shown
     * @param breedWikiUrl: the url of the wikipedia page of the breed
     * @throws IOException
     */
    public void openWiki(ActionEvent event, CatData cat, String breedWikiUrl) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("wiki-view.fxml"));
        Parent root = fxmlLoader.load();

        WikiController wikiController = fxmlLoader.getController();
        wikiController.setCatData(cat);
        wikiController.setWikiUrl(breedWikiUrl);
        wikiController.initializeUI();

        Scene scene = new Scene(root, 800, 600);
        // Load BootstrapFX stylesheet as well as customized CSS stylesheet
        scene.getStylesheets().addAll(
                BootstrapFX.bootstrapFXStylesheet(),
                getClass().getResource("styles.css").toExternalForm());

        //gets the stage info
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("Wikipedia [External]");
        stage.setScene(scene);
        stage.show();
    }
}
