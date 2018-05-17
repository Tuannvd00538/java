/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reading.news.controller;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import reading.news.entity.User;
import reading.news.model.UserModel;
import static reading.news.view.Main.stage;
import org.json.*;

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
    @FXML
    private ImageView avatar;
    @FXML
    private Button nextToMainBtn;
    @FXML
    private TextField fullName;
    @FXML
    private DatePicker birthDay;

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
    private void loginAction() throws IOException {
        String username = usernameLogin.getText();
        String password = passwordLogin.getText();
        User us = um.login(username, password);
        if (us != null) {
            nextToMain();
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
    private void registerButton() throws IOException {
        int id = (int) System.currentTimeMillis();
        String username = usernameRegister.getText();
        String password = passwordRegister.getText();
        String email = emailRegister.getText();
        User user = new User(id, username, password, email);
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
            System.out.println("Please tick");
        } else if (um.checkExitUser(username)) {
            System.out.println("Tài khoản đã tồn tại!");
        } else if (um.register(user)) {
            FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/reading/news/design/Avatar.fxml"));
            Parent register = fxmlloader.load();
            stage.getScene().setRoot(register);
            System.out.println("Đăng ký thành công!");
        } else {
            System.err.println("Có lỗi xảy ra!");
        }
    }

    @FXML
    public void uploadAvatar(File file) {
        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteArray);
            byte[] byteImage = byteArray.toByteArray();
            String dataImage = Base64.encode(byteImage);
            String data = URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(dataImage, "UTF-8");
            URL url = new URL("https://api.imgur.com/3/image");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "Client-ID ecea6e24d5bff94");
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            conn.connect();
            StringBuilder stb = new StringBuilder();
            BufferedReader rd;
            try (OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream())) {
                wr.write(data);
                wr.flush();
                // Get the response
                rd = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    stb.append(line).append("\n");
                }
            }
            rd.close();
            String jsonString = stb.toString();
            JSONObject objectJson = new JSONObject(jsonString);
            String abc = objectJson.getJSONObject("data").getString("link");
            String fullname = this.fullName.getText();
            LocalDate birthday = this.birthDay.getValue();
            String bd = birthday.toString();
            if (um.setInfo(abc, fullname, bd)) {
                avatar.setImage(image);
                nextToMainBtn.setDisable(false);
            }
        } catch (IOException ex) {
            Logger.getLogger(DesignController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void chooseAvatar() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
        File file = fileChooser.showOpenDialog(null);
        if (file == null) {
            System.out.println("Vui lòng chọn ảnh");
        } else {
            uploadAvatar(file);
        }
    }

    @FXML
    public void nextToMain() throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("/reading/news/design/MainChat.fxml"));
        Parent register = fxmlloader.load();
        stage.getScene().setRoot(register);
        MainChatController mcc = new MainChatController();
        mcc.getInfo();
    }
}
