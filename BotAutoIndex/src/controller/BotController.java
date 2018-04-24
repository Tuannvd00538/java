/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import botthread.BotThreadDelay;
import entity.Bot;
import java.io.IOException;
import java.util.HashSet;
import java.util.Scanner;
import model.BotModel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Ngo Van Tuan
 */
public class BotController {
    
    HashSet<Bot> setUrl = new HashSet<Bot>();
    BotModel bm = new BotModel();
    Scanner input = new Scanner(System.in);
    
    public void getLink() {
        try {
            Document doc = Jsoup.connect("http://genk.vn/").get();
            Elements list = doc.select("body a");
            for (Element link : list) {
                String url = link.attr("href");
                String title = link.attr("title");
                if (url.contains(".chn") && title.length() > 20) {
                    if (url.contains("http://genk.vn")) {
                        setUrl.add(new Bot(title, url));
                    } else {
                        String newUrl = "http://genk.vn" + url;
                        setUrl.add(new Bot(title, newUrl));
                    }
                }
            }
            for (Bot hihi : setUrl) {
                bm.getUrl(hihi.getUrl(), hihi.getName());
                BotThreadDelay btd = new BotThreadDelay();
                btd.start();
            }
        } catch (IOException e) {
            System.err.println("Không thể lấy tin! " + e);
        }
    }
    
    public int indexArticles;
    
    public void choiceArticle() {
        System.out.println("---------------------------");
        System.out.println("Chọn tin bạn muốn đọc:");
        int choice = Integer.parseInt(input.nextLine());
        indexArticles = choice - 1;
        switch(choice) {
            case 0:
                break;
            default:
                switch(bm.getDetail(indexArticles)) {
                    case 0:
                        bm.getIndex();
                        break;
                    default:
                        break;
                }
                break;
        }
    }
}
