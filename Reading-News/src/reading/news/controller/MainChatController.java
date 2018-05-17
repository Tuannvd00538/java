/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reading.news.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import reading.news.model.UserModel;

/**
 * FXML Controller class
 *
 * @author biidz
 */
public class MainChatController implements Initializable {

    @FXML
    private ImageView avatarMain;
    @FXML
    private Label nameMain;
    @FXML
    UserModel um = new UserModel();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void getInfo() {
        nameMain.setText("Siin");
        System.out.println(UserModel.siindeptrai.getFullName());
    }

}
