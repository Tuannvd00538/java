
import botthread.BotThreadDelay;
import entity.Bot;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author biidz
 */
public class Test {

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("http://genk.vn/flagship-android-4-nam-truoc-chung-ta-theo-duoi-bay-gio-ra-sao-20180406181450674.chn").get();
            Elements list = doc.select("body div.kbw-content div.VCSortableInPreviewMode img");
            for (Element link : list) {
                System.out.println(link);
            }
        } catch (IOException e) {
            System.err.println("Không thể lấy tin! " + e);
        }
    }
}
