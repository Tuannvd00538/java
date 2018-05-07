/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siinreader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author biidz
 */
public class SiinReader extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("SiinReader.fxml"));
        Parent root = fxmlloader.load();
        fxmlloader.<SiinReaderController>getController().getArticles();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("Siin Reader");
        stage.setScene(scene);
        stage.getIcons().add(new Image(SiinReader.class.getResourceAsStream("img/icon.png")));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
