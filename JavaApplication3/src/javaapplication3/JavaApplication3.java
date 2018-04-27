/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

/**
 *
 * @author biidz
 */
public class JavaApplication3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
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
//
//        Scanner input = new Scanner(System.in);
//        JavaApplication3 jav = new JavaApplication3();
//        System.out.println("-----------------------");
//        System.out.println("1. List Student");
//        System.out.println("2. Add Student");
//        System.out.println("-----------------------");
//        System.out.println("Please enter your choice:");
//        int choice = Integer.parseInt(input.nextLine());
//        switch(choice) {
//            case 1:
//                jav.read();
//                break;
//            case 2:
//                jav.add();
//                break;
//            default:
//                break;
//        }

    }

    public void read() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("siin.txt");
        BufferedReader br = new BufferedReader(fr);
        int i;
        while ((i = br.read()) != -1) {
            System.out.print((char) i);
        }
        br.close();
        fr.close();
    }

    public void add() throws IOException {
        FileWriter fw = new FileWriter("siin.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Siin Dep Trai");
        bw.newLine();
        bw.close();
        System.out.println("add success!");
    }

}
