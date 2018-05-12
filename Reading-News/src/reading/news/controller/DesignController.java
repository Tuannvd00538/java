/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reading.news.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import reading.news.entity.User;
import reading.news.model.UserModel;
import static reading.news.view.Main.stage;

/**
 *
 * @author biidz
 */
public class DesignController implements Initializable {

    @FXML
    private Pane start;
    @FXML
    private TextField usernameLogin;
    private Button usernameLoginBtn;
    @FXML
    private PasswordField passwordLogin;
    @FXML
    private Button nextUsername;

    UserModel um = new UserModel();
    @FXML
    private Button loginButton;
    @FXML
    private AnchorPane RegisterPane;
    @FXML
    private TextField usernameRegister;
    @FXML
    private PasswordField passwordRegister;
    @FXML
    private PasswordField repassRegister;
    @FXML
    private TextField emailRegister;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void checkEmpty() {
        if (!usernameLogin.getText().trim().isEmpty() && usernameLogin.getText().length() >= 5) {
            nextUsername.setDisable(false);
        } else {
            nextUsername.setDisable(true);
        }
        usernameLogin.setStyle("-fx-border-color: #999");
        if (!passwordLogin.getText().trim().isEmpty() && passwordLogin.getText().length() >= 5) {
            loginButton.setDisable(false);
        } else {
            loginButton.setDisable(true);
        }
        passwordLogin.setStyle("-fx-border-color: #999");
    }

    @FXML
    private void checkExitUsername() {
        String username = usernameLogin.getText();
        if (um.checkExitUser(username)) {
            passwordLogin.setVisible(true);
            loginButton.setVisible(true);
            nextUsername.setVisible(false);
        } else {
            usernameLogin.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    private void loginAction() {
        String username = usernameLogin.getText();
        String password = passwordLogin.getText();
        User us = um.login(username, password);
        if (us != null) {
            System.out.println(us.getUsername());
        } else {
            passwordLogin.setStyle("-fx-border-color: red");
        }
    }

    @FXML
    private void registerPane() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/reading/news/design/Register.fxml"));
        Parent register = fxmlloader.load();
        stage.getScene().setRoot(register);
    }

    @FXML
    private void loginClickPane() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/reading/news/design/Login.fxml"));
        Parent register = fxmlloader.load();
        stage.getScene().setRoot(register);
    }

    @FXML
    private void registerButton() {
        String username = usernameRegister.getText();
        String password = passwordRegister.getText();
        String email = emailRegister.getText();
        User user = new User(username, password, email);
        if (um.register(user)) {
            System.out.println("Đăng ký thành công!");
        } else {
            System.err.println("Có lỗi xảy ra!");
        }
    }

}
