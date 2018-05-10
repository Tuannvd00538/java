/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package siinreader;

import com.sun.javafx.scene.control.skin.LabeledText;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 *
 * @author biidz
 */
public class SiinReaderController implements Initializable {

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
    private Pane edit;

    @FXML
    ArrayList<Bot> list = new ArrayList(Arrays.asList());

    @FXML
    ListView<String> listArt = new ListView<>();

    @FXML
    HashMap siin = new HashMap();

    @FXML
    private TextFlow content;

    @FXML
    private TextField titleArticle;

    @FXML
    private TextArea contentArticle;

    @FXML
    ContextMenu contextMenu = new ContextMenu();

    @FXML
    MenuItem sua = new MenuItem("Sửa");

    @FXML
    MenuItem xoa = new MenuItem("Xóa");

    @FXML
    private TextField valueSearch;

    @FXML
    public void getArticles() {
        long start = System.currentTimeMillis();
        try {
            Statement stt = BotDBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM articles WHERE status = 1 ORDER BY createAt DESC");
            ObservableList<String> items = FXCollections.observableArrayList();
            siin.clear();
            listArticles.getItems().clear();
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
                listArticles.refresh();
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
            } catch (InterruptedException ex) {
                Logger.getLogger(SiinReaderController.class.getName()).log(Level.SEVERE, null, ex);
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
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("VnExpress");
        alert.setContentText("Chức năng đang phát triển!");
        alert.showAndWait();
    }

    @FXML
    public void dantri() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
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
            } catch (InterruptedException ex) {
                Logger.getLogger(SiinReaderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    @FXML
    public void cancel() {
        detailPage.setVisible(false);
        articlePageGenk.setVisible(true);
    }

    @FXML
    String siindz = null;

    @FXML
    String urlUpdate = null;

    @FXML
    public void edit() {
        long start = System.currentTimeMillis();
        try {
            Statement stt = BotDBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM articles WHERE title = '" + siindz + "'");
            if (rs.next()) {
                titleArticle.clear();
                titleArticle.setText(rs.getString("title"));
                contentArticle.clear();
                contentArticle.setText(rs.getString("content"));
                contentArticle.setWrapText(true);
                urlUpdate = rs.getString("url");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        long end = System.currentTimeMillis();
        int time = (int) (end - start);
        new Thread(() -> {
            try {
                Thread.sleep(time);
                edit.setVisible(true);
                articlePageGenk.setVisible(false);
            } catch (InterruptedException ex) {
                Logger.getLogger(SiinReaderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    @FXML
    public void saveEdit() {
        long start = System.currentTimeMillis();
        try {
            Statement stt = BotDBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM articles WHERE url = '" + urlUpdate + "'");
            if (rs.next()) {
                String sql = "update articles set title = ?, content = ? where articles.url = ?";
                PreparedStatement ps = BotDBConnection.getInstance().getConnection().prepareStatement(sql);
                ps.setString(1, titleArticle.getText());
                ps.setString(2, contentArticle.getText());
                ps.setString(3, urlUpdate);
                ps.execute();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        long end = System.currentTimeMillis();
        int time = (int) (end - start);
        new Thread(() -> {
            try {
                Thread.sleep(time);
                getArticles();
                articlePageGenk.setVisible(true);
                edit.setVisible(false);
            } catch (InterruptedException ex) {
                Logger.getLogger(SiinReaderController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    @FXML
    public void huy() {
        articlePageGenk.setVisible(true);
        edit.setVisible(false);
    }

    @FXML
    public void quaylai() {
        articlePageGenk.setVisible(false);
        choice.setVisible(true);
    }

    @FXML
    public void search() {
        try {
            Statement stt = BotDBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM articles WHERE title LIKE '%" + valueSearch.getText() + "%' AND status = 1");
            ObservableList<String> items = FXCollections.observableArrayList();
            siin.clear();
            listArticles.getItems().clear();
            list.clear();
            if (rs.next()) {
                while (rs.next()) {
                    String title = rs.getString("title");
                    String urlDB = rs.getString("url");
                    Bot bot = new Bot(title, urlDB);
                    list.add(bot);
                }
                for (int i = 0; i < list.size(); i++) {
                    String title = list.get(i).getName();
                    String url = list.get(i).getUrl();
                    items.add(title);
                    siin.put(title, url);
                    listArt.setItems(items);
                }
                listArticles.setItems(items);
                listArticles.refresh();
            } else {
                System.err.println("Không có bài viết nào khớp với từ khóa tìm kiếm!");
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    @FXML
    public void delete() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setContentText("Bạn có chắc chắn muốn xóa?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            try {
                String sql = "DELETE FROM articles WHERE title = ?";
                PreparedStatement ps = BotDBConnection.getInstance().getConnection().prepareStatement(sql);
                ps.setString(1, siindz);
                ps.execute();
                System.out.println("Xóa thành công!");
            } catch (SQLException e) {
                System.err.println(e);
            }
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listArticles.setOnMouseClicked((MouseEvent event) -> {
            if ((event.getTarget() instanceof LabeledText || ((GridPane) event.getTarget()).getChildren().size() > 0)
                    && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
                LabeledText lbl = (LabeledText) event.getTarget();
                getDetail(lbl.getText());
            }
            if (event.getButton() == MouseButton.SECONDARY && (event.getTarget() instanceof LabeledText || ((GridPane) event.getTarget()).getChildren().size() > 0)) {
                LabeledText lbl = (LabeledText) event.getTarget();
                siindz = lbl.getText();
            }
        });
        contextMenu.getItems().addAll(sua, xoa);
        listArticles.setOnContextMenuRequested((ContextMenuEvent event) -> {
            contextMenu.show(listArticles, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        sua.setOnAction((ActionEvent event) -> {
            edit();
        });
        xoa.setOnAction((ActionEvent event) -> {
            delete();
        });
    }
}
