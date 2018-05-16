/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reading.news.view;

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
public class Main extends Application {
    
    public static Stage stage = null;
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/reading/news/design/Login.fxml"));
        Parent root = fxmlloader.load();
        Scene scene = new Scene(root, 1000, 600);
        stage.setTitle("iSiin");
        stage.getIcons().add(new Image(Main.class.getResourceAsStream("/reading/news/img/icon.png")));
        Main.stage = stage;
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
