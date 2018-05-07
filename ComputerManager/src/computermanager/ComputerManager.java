/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package computermanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author biidz
 */
public class ComputerManager extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("ComputerManager.fxml"));
        Parent root = fxmlloader.load();
        fxmlloader.<ComputerManagerController>getController().getTable();
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("CHUONG TRINH QUAN LY MAY TINH");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
