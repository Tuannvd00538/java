/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botfx;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.BotDBConnection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author biidz
 */
public class BotModel {

    public void botDelay() {
        try {
            Connection cnn = BotDBConnection.getInstance().getConnection();
            Statement stt = cnn.createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM articles WHERE status = 0 LIMIT 200");
            if (rs.next()) {
                String urlGet = rs.getString("url");
                Document doc = Jsoup.connect(urlGet).get();
                String content1 = doc.select("h2.knc-sapo").text();
                Elements content2 = doc.select("#ContentDetail p");
                StringBuilder contentAll = new StringBuilder();
                for (Element element : content2) {
                    contentAll.append(element.text()).append("\n");
                }
                String content = content1 + "\n" + contentAll;
                ResultSet rrs = stt.executeQuery("SELECT * FROM articles WHERE url = '" + urlGet + "'");
                if (rrs.next()) {
                    String sql = "update articles set content = ?, status = 1 where url = ?";
                    PreparedStatement ps = cnn.prepareStatement(sql);
                    ps.setString(1, content);
                    ps.setString(2, urlGet);
                    ps.execute();
                }
                Elements list = doc.select("body a");
                for (Element link : list) {
                    String url = link.attr("href");
                    String title = link.attr("title");
                    if (url.contains(".chn") && title.length() > 20) {
                        if (url.contains("http://genk.vn")) {
                            ResultSet rss = stt.executeQuery("SELECT * FROM articles WHERE url = '" + url + "'");
                            if (!rss.next()) {
                                String sql = "insert into articles (title, url) values (?, ?)";
                                PreparedStatement ps = cnn.prepareStatement(sql);
                                ps.setString(1, title);
                                ps.setString(2, url);
                                ps.execute();
                            }
                        } else {
                            String newUrl = "http://genk.vn" + url;
                            ResultSet rss = stt.executeQuery("SELECT * FROM articles WHERE url = '" + newUrl + "'");
                            if (!rss.next()) {
                                String sql = "insert into articles (title, url) values (?, ?)";
                                PreparedStatement ps = cnn.prepareStatement(sql);
                                ps.setString(1, title);
                                ps.setString(2, newUrl);
                                ps.execute();
                            }
                        }
                    }
                }
                System.out.println("Get content in " + Thread.currentThread().getName());
            } else {
                System.err.println("Da get het bai viet!");
            }
        } catch (IOException | SQLException e) {
            System.err.println(e);
        }
    }
}
