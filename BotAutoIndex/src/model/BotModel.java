/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import botthread.BotThread;
import controller.BotController;
import entity.Bot;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ngo Van Tuan
 */
public class BotModel {

    public static ArrayList<Bot> articles = new ArrayList<>(Arrays.asList());

    Scanner input = new Scanner(System.in);

    public void getIndex() {
        BotController ctrl = new BotController();

        try {
            Statement stt = BotDBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM articles WHERE status = 1 ORDER BY createAt DESC");
            if (rs.next()) {
                if (articles.isEmpty()) {
                    while (rs.next()) {
                        String title = rs.getString("title");
                        String urlDB = rs.getString("url");
                        Bot bot = new Bot(title, urlDB);
                        articles.add(bot);
                    }
                }
                for (int i = 0; i < articles.size(); i++) {
                    System.out.println((i + 1) + ". " + articles.get(i).getName());
                }
                ctrl.choiceArticle();
            } else {
                ctrl.getLink();
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public void getUrl(String url, String title) {
        try {
            Connection cnn = BotDBConnection.getInstance().getConnection();
            Statement stt = cnn.createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM articles WHERE url = '" + url + "'");
            if (!rs.next()) {
                String sql = "insert into articles (title, url) values (?, ?)";
                PreparedStatement ps = cnn.prepareStatement(sql);
                ps.setString(1, title);
                ps.setString(2, url);
                ps.execute();
            }
            System.out.println(" - " + title);
            BotThread siin = new BotThread(url);
            siin.start();
            siin.join();
        } catch (InterruptedException | SQLException e) {
            System.err.println(e);
        }
    }

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
            }
        } catch (IOException | SQLException e) {
            System.err.println(e);
        }
    }

    public int getDetail(int indexArticles) {
        System.out.println("===================================");
        System.out.println(articles.get(indexArticles).getName());
        System.out.println("-----------------------------------");
        try {
            Statement stt = BotDBConnection.getInstance().getConnection().createStatement();
            ResultSet rs = stt.executeQuery("SELECT * FROM articles WHERE url = '" + articles.get(indexArticles).getUrl() + "'");
            if (rs.next()) {
                System.out.println(rs.getString("content"));
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        System.out.println("-----------------------------------");
        System.out.println("0. Quay lại");
        int choice = Integer.parseInt(input.nextLine());
        return choice;
    }
}
