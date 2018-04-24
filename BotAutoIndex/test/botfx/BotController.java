/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botfx;

import entity.Bot;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
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
    private Pane articlePage;

    @FXML
    ArrayList<Bot> list = new ArrayList(Arrays.asList());

    @FXML
    ListView<String> listArt = new ListView<>();

    @FXML
    public void getArticles() {
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
                    listArt.setItems(items);
                }
                listArticles.setItems(items);
            } else {
                System.out.println("Chưa có bài viết nào trong db!");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        new Thread(() -> {
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(BotController.class.getName()).log(Level.SEVERE, null, ex);
            }
            startPage.setVisible(false);
            articlePage.setVisible(true);
        }).start();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}
