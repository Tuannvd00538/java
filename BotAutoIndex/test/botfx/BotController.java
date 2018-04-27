/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botfx;

import com.sun.javafx.scene.control.skin.LabeledText;
import entity.Bot;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import model.BotDBConnection;

/**
 *
 * @author biidz
 */
public class BotController implements Initializable {

    @FXML
    private ListView listArticles;

    @FXML
    private Pane startPage;

    @FXML
    private Pane articlePageGenk;

    @FXML
    private Pane choice;

    @FXML
    private Pane detailPage;

    @FXML
    ArrayList<Bot> list = new ArrayList(Arrays.asList());

    @FXML
    ListView<String> listArt = new ListView<>();

    @FXML
    HashMap siin = new HashMap();

    @FXML
    private TextFlow content;

    @FXML
    public void getArticles() {
        long start = System.currentTimeMillis();
        try {
            Statement stt = BotDBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM articles WHERE status = 1 ORDER BY createAt DESC");
            ObservableList<String> items = FXCollections.observableArrayList();
            if (rs.next()) {
                if (list.isEmpty()) {
                    while (rs.next()) {
                        String title = rs.getString("title");
                        String urlDB = rs.getString("url");
                        Bot bot = new Bot(title, urlDB);
                        list.add(bot);
                    }
                }
                for (int i = 0; i < list.size(); i++) {
                    String title = list.get(i).getName();
                    String url = list.get(i).getUrl();
                    items.add(title);
                    siin.put(title, url);
                    listArt.setItems(items);
                }
                listArticles.setItems(items);
            } else {
                System.err.println("Chưa có bài viết nào trong database!");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        long end = System.currentTimeMillis();
        int time = (int) (end - start);
        new Thread(() -> {
            try {
                Thread.sleep(time);
                startPage.setVisible(false);
                choice.setVisible(true);
//                BotModel bm = new BotModel();
//                bm.botDelay();
            } catch (InterruptedException ex) {
                Logger.getLogger(BotController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    @FXML
    public void genk() {
        choice.setVisible(false);
        articlePageGenk.setVisible(true);
    }

    @FXML
    public void vnexpress() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("VnExpress");
        alert.setContentText("Chức năng đang phát triển!");
        alert.showAndWait();
    }

    @FXML
    public void dantri() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("DanTri");
        alert.setContentText("Chức năng đang phát triển!");
        alert.showAndWait();
    }

    @FXML
    public void getDetail(String title) {
        long start = System.currentTimeMillis();
        String url = (String) siin.get(title);
        try {
            Statement stt = BotDBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM articles WHERE url = '" + url + "'");
            if (rs.next()) {
                content.getChildren().clear();
                Text contentText = new Text(rs.getString("content"));
                contentText.setFont(new Font(14));
                content.getChildren().add(contentText);
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        long end = System.currentTimeMillis();
        int time = (int) (end - start);
        new Thread(() -> {
            try {
                Thread.sleep(time);
                articlePageGenk.setVisible(false);
                detailPage.setVisible(true);
//                BotModel bm = new BotModel();
//                bm.botDelay();
            } catch (InterruptedException ex) {
                Logger.getLogger(BotController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    @FXML
    public void cancel() {
        detailPage.setVisible(false);
        articlePageGenk.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listArticles.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2
                    && (event.getTarget() instanceof LabeledText || ((GridPane) event.getTarget()).getChildren().size() > 0)) {
                LabeledText lbl = (LabeledText) event.getTarget();
                getDetail(lbl.getText());
            }
        });
    }
}
