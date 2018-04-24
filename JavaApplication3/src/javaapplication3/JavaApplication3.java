/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 *
 * @author biidz
 */
public class JavaApplication3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            URL url = new URL("http://genk.vn/");
            URLConnection conn = url.openConnection();
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                StringBuilder siin = new StringBuilder();
                while ((inputLine = br.readLine()) != null) {
                    siin.append(inputLine).append("\n");
                    System.out.println(inputLine);
                }
                Writer writer = null;
                try {
                    File theDir = new File("save_pages");
                    if (!theDir.exists()) {
                        theDir.mkdir();
                    }
                    File index = new File(theDir + "/" + "index.html");
                    Filename myHomePage = new Filename(index, '/', '.');
                    int i = 0;
                    if (!index.exists()) {
                        writer = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream(index), "utf-8"));
                        writer.write(siin.toString());
                    } else if (index.exists()) {
                        i++;
                        writer = new BufferedWriter(new OutputStreamWriter(
                                new FileOutputStream(theDir + "/" + "index" + i + ".html"), "utf-8"));
                        writer.write(siin.toString());
                    }
                } catch (IOException ex) {
                } finally {
                    try {
                        writer.close();
                    } catch (IOException ex) {
                    }
                }
            }
        } catch (MalformedURLException e) {
            e.getMessage();
        } catch (IOException e) {
            e.getMessage();
        }

    }

}
