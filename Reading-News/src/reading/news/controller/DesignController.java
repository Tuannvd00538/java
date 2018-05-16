/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reading.news.controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import reading.news.entity.User;
import reading.news.model.UserModel;
import static reading.news.view.Main.stage;

/**
 *
 * @author biidz
 */
public class DesignController implements Initializable {

    @FXML
    private TextField usernameLogin;
    @FXML
    private PasswordField passwordLogin;
    @FXML
    private Button nextUsername;
    @FXML
    UserModel um = new UserModel();
    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameRegister;
    @FXML
    private PasswordField passwordRegister;
    @FXML
    private PasswordField repassRegister;
    @FXML
    private TextField emailRegister;
    @FXML
    private CheckBox confirmTerm;

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
    public void checkEmptyRegister() {
        usernameRegister.setStyle("-fx-border-color: #999");
        passwordRegister.setStyle("-fx-border-color: #999");
        emailRegister.setStyle("-fx-border-color: #999");
        repassRegister.setStyle("-fx-border-color: #999");
    }

    @FXML
    private void checkExitUsername() {
        String username = usernameLogin.getText();
        if (um.checkExitUser(username)) {
            passwordLogin.setVisible(true);
            loginButton.setVisible(true);
            nextUsername.setVisible(false);
            passwordLogin.requestFocus();
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
    private HashMap<String, String> erorrs(User user) {
        HashMap<String, String> errors = new HashMap<>();
        if (user.getUsername().isEmpty()) {
            errors.put("Username", "Username is empty");
        } else if (user.getUsername().length() < 5) {
            errors.put("Username", "Username must be greater than 5 characters");
        }
        if (user.getPassword().isEmpty()) {
            errors.put("Password", "Password is empty");
        } else if (user.getPassword().length() < 5) {
            errors.put("Password", "Password must be greater than 5 chareacters");
        }
        if (repassRegister.getText().isEmpty()) {
            errors.put("Repass", "Confirm password is empty");
        } else if (!repassRegister.getText().equals(user.getPassword())) {
            errors.put("Repass", "Confirm password is incorrect");
        }
        if (user.getEmail().isEmpty()) {
            errors.put("Email", "Email is empty");
        } else if (!isValidEmailAddress(user.getEmail())) {
            errors.put("Email", "Email invalidate");
        }
        return errors;
    }

    @FXML
    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    @FXML
    private void registerButton() {
        String username = usernameRegister.getText();
        String password = passwordRegister.getText();
        String email = emailRegister.getText();
        User user = new User(username, password, email);
        HashMap<String, String> err = erorrs(user);
        if (!err.isEmpty()) {
            for (Map.Entry<String, String> entry : err.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (key.equals("Username")) {
                    usernameRegister.setStyle("-fx-border-color: red");
                    System.out.println(value);
                } else if (key.equals("Password")) {
                    passwordRegister.setStyle("-fx-border-color: red");
                    System.out.println(value);
                } else if (key.equals("Repass")) {
                    repassRegister.setStyle("-fx-border-color: red");
                    System.out.println(value);
                } else if (key.equals("Email")) {
                    emailRegister.setStyle("-fx-border-color: red");
                    System.out.println(value);
                }
            }
            return;
        } else if (!confirmTerm.isSelected()) {
            System.err.println("Please tick");
        } else if (um.checkExitUser(username)) {
            System.err.println("Tài khoản đã tồn tại!");
        } else if (um.register(user)) {
            System.out.println("Đăng ký thành công!");
        } else {
            System.err.println("Có lỗi xảy ra!");
        }
    }
}
