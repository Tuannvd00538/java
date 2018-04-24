/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package botthread;

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
 * @author Ngo Van Tuan
 */
public class BotThread extends Thread{
    
    private final String url;
    
    public BotThread(String url) {
        this.url = url;
    }
    
    @Override
    public void run() {
        try {
            Document doc = Jsoup.connect(this.url).get();
            String content1 = doc.select("h2.knc-sapo").text();
            Elements content2 = doc.select("#ContentDetail p");
            StringBuilder contentAll = new StringBuilder();
            for (Element element : content2) {
                contentAll.append(element.text()).append("\n");
            }
            String content = content1 + "\n" + contentAll;
            Connection cnn = BotDBConnection.getInstance().getConnection();
            Statement stt = cnn.createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM articles WHERE url = '" + this.url + "'");
            if (rs.next()) {
                String sql = "update articles set content = ?, status = 1 where url = ?";
                PreparedStatement ps = cnn.prepareStatement(sql);
                ps.setString(1, content);
                ps.setString(2, this.url);
                ps.execute();
            }
            Elements list = doc.select("body a");
            for (Element link : list) {
                String thisUrl = link.attr("href");
                String title = link.attr("title");
                if (thisUrl.contains(".chn") && title.length() > 20) {
                    if (thisUrl.contains("http://genk.vn")) {
                        ResultSet rss = stt.executeQuery("SELECT * FROM articles WHERE url = '" + thisUrl + "'");
                        if (!rss.next()) {
                            String sql = "insert into articles (title, url) values (?, ?)";
                            PreparedStatement ps = cnn.prepareStatement(sql);
                            ps.setString(1, title);
                            ps.setString(2, thisUrl);
                            ps.execute();
                        }
                    } else {
                        String newUrl = "http://genk.vn" + thisUrl;
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
        } catch (IOException | SQLException e) {
            System.out.println(e);
        }
    }
}
